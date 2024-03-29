/*
 * Copyright (C) 2005 - 2008 JasperSoft Corporation.  All rights reserved. 
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from JasperSoft,
 * the following license terms apply:
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; and without the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/gpl.txt
 * or write to:
 *
 * Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330,
 * Boston, MA  USA  02111-1307
 *
 *
 *
 *
 * XMLTokenMarker.java
 * 
 */

package org.syntax.jedit.tokenmarker;

/**
 * XML token marker.
 *
 * @author Slava Pestov
 * @version $Id: XMLTokenMarker.java 1167 2008-01-15 18:49:05Z gtoffoli $
 */
public class XMLTokenMarker extends HTMLTokenMarker
{
	public XMLTokenMarker()
	{
		super(false);
	}
}
