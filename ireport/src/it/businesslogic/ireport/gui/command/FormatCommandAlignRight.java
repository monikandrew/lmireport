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
 * FormatCommandAlignRight.java
 * 
 * Created on 9 mei 2005, 21:36
 *
 */

package it.businesslogic.ireport.gui.command;

import it.businesslogic.ireport.OperationType;
import it.businesslogic.ireport.ReportElement;
import java.awt.Point;

/**
 *
 */
public class FormatCommandAlignRight extends FormatCommand {
    {
        operationType = OperationType.ALIGN_TOP;
    }
    
    int right;
    // Preparation is called before the while loop
    
    public void preparation() {
        right = ((ReportElement) this.getSelectedElements().firstElement()).getPosition().x +
                ((ReportElement) this.getSelectedElements().firstElement()).getWidth();
        
    }
    
    public void modify() {
        re.setPosition(new Point(right-re.getWidth(), re.getPosition().y));
    }
    
}
