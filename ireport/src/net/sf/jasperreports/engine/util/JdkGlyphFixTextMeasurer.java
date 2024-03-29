/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */
package net.sf.jasperreports.engine.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JRCommonText;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.fill.JRMeasuredText;
import net.sf.jasperreports.engine.fill.TextMeasurer;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.util.JRStyledText;

/**
 * A text measurer implementation that extends
 * {@link TextMeasurer the default text measurer} and adds a workaround for
 * Sun JDK bug <a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6367148">6367148</a>/
 * <a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6611637">6611637</a>.
 * 
 * <p>
 * The workaround consists of simply reattempting the text measuring when 
 * a <code>java.lang.NullPointer</code> exception is thrown from
 * <code>sun.font.GlyphLayout</code>. 
 * </p>
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JdkGlyphFixTextMeasurer.java 2146 2008-04-01 10:26:26Z lucianc $
 * @see JdkGlyphFixTextMeasurerFactory
 */
public class JdkGlyphFixTextMeasurer extends TextMeasurer
{

	private static final Log log = LogFactory.getLog(JdkGlyphFixTextMeasurer.class);
	
	protected static final String JDK_EXCEPTION_CLASS_PREFIX = "sun.font.GlyphLayout";
	
	/**
	 * The default attempt count.
	 */
	public static final int DEFAULT_ATTEMPTS = 20;
	
	/**
	 * The default between attempts sleep time.
	 */
	public static final int DEFAULT_ATTEMPT_SLEEP = 0;
	
	/**
	 * A property that specifies the number of times the measurer should attempt
	 * to measure a single text element before giving up.
	 * 
	 * The default value is 20.
	 * 
	 * @see #DEFAULT_ATTEMPTS
	 */
	public static final String PROPERTY_ATTEMPTS = JRProperties.PROPERTY_PREFIX 
			+ "jdk.glyph.fix.text.measurer.attempts";
	
	/**
	 * A property that specifies the number of milliseconds to sleep between
	 * measuring reattempts.
	 * 
	 * The default value is 0, which means that the measurer will not pause
	 * between reattempts.
	 * 
	 * @see #DEFAULT_ATTEMPT_SLEEP
	 */
	public static final String PROPERTY_ATTEMPT_SLEEP = JRProperties.PROPERTY_PREFIX 
			+ "jdk.glyph.fix.text.measurer.sleep";
	
	/**
	 * Whether <code>java.lang.NullPointer</code> exceptions with empty stacktraces
	 * should be caught.
	 * 
	 * This is useful when running on a Sun server JVM (java -server), which might omit
	 * exception stacktraces in some cases.
	 */
	public static final String PROPERTY_CATCH_EMPTY_STACKTRACE = JRProperties.PROPERTY_PREFIX 
			+ "jdk.glyph.fix.text.measurer.catch.empty.stakctrace";
	
	private final int attempts;
	private final int sleep;
	private final boolean catchEmptyStacktrace;
	
	/**
	 * Create a text measurer for a text element.
	 * 
	 * @param textElement the text element
	 */
	public JdkGlyphFixTextMeasurer(JRCommonText textElement)
	{
		super(textElement);
		
		attempts = JRProperties.getIntegerProperty(PROPERTY_ATTEMPTS, DEFAULT_ATTEMPTS);
		sleep = JRProperties.getIntegerProperty(PROPERTY_ATTEMPT_SLEEP, DEFAULT_ATTEMPT_SLEEP);
		catchEmptyStacktrace = JRProperties.getBooleanProperty(PROPERTY_CATCH_EMPTY_STACKTRACE);
	}

	/**
	 * Calls {@link TextMeasurer#measure(JRStyledText, int, int, boolean) super.measure}, catches
	 * <code>sun.font.GlyphLayout</code> NPEs and reattempts the call.
	 */
	public JRMeasuredText measure(JRStyledText styledText, int remainingTextStart, int availableStretchHeight, boolean canOverflow)
	{
		int count = 0;
		do
		{
			try
			{
				++count;

				return super.measure(styledText, remainingTextStart, availableStretchHeight, canOverflow);
			}
			catch (NullPointerException e)
			{
				if (isJdkGlyphError(e))
				{
					if (count >= attempts)
					{
						log.error("JDK Glyph exception caught " + attempts + " times, giving up attempts");
						throw e;
					}
				}
				else
				{
					throw e;
				}
				
				if (log.isDebugEnabled())
				{
					log.debug("Caught JDK Glyph exception " + e + " at attempt #" + count);
				}
				
				if (sleep > 0)
				{
					try
					{
						Thread.sleep(sleep);
					}
					catch (InterruptedException ie)
					{
						throw new JRRuntimeException(ie);
					}
				}
			}
		}
		while(true);
	}

	protected boolean isJdkGlyphError(NullPointerException e)
	{
		StackTraceElement[] stackTrace = e.getStackTrace();
		if (stackTrace.length == 0)
		{
			if (log.isDebugEnabled())
			{
				log.debug("Caught exception with no stacktrace; " 
						+ (catchEmptyStacktrace ? "" : "not ") + "treating as JDK Glyph exception");
			}
			
			return catchEmptyStacktrace;
		}
		
		StackTraceElement top = stackTrace[0];
		return top.getClassName().startsWith(JDK_EXCEPTION_CLASS_PREFIX);
	}
	
}
