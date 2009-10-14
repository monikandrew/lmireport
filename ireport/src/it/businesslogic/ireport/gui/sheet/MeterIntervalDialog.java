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
 * MeterIntervalDialog.java
 * 
 * Created on 17 agosto 2005, 11.19
 *
 */

package it.businesslogic.ireport.gui.sheet;

import it.businesslogic.ireport.SubDataset;
import it.businesslogic.ireport.chart.DataRange;
import it.businesslogic.ireport.chart.MeterInterval;
import it.businesslogic.ireport.util.Misc;
import javax.swing.SpinnerNumberModel;
import it.businesslogic.ireport.util.I18n;

/**
 *
 * @author  Administrator
 */
public class MeterIntervalDialog extends javax.swing.JDialog {
    
    private MeterInterval meterInterval = null;
    
    private int dialogResult = javax.swing.JOptionPane.CANCEL_OPTION;
    
    /** Creates new form CategorySeriesDialog */
    public MeterIntervalDialog(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        applyI18n();
        
        this.setSize(500,500);
        it.businesslogic.ireport.util.Misc.centerFrame(this);
        
        javax.swing.KeyStroke escape =  javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0, false);
        javax.swing.Action escapeAction = new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jButtonCancelActionPerformed(e);
            }
        };
       
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);
        
        
        jSpinnerAlpha.setModel(new SpinnerNumberModel(1.0d, 0.0d, 1.0d, 0.01d));
        
        //to make the default button ...
        this.getRootPane().setDefaultButton(this.jButtonOK);
    }
    
    
    /**
     * this method is used to pass the correct subdataset to the expression editor
     */
    public void setSubDataset( SubDataset sds )
    {
        jRTextExpressionHigh.setSubDataset(sds);
        jRTextExpressionLow.setSubDataset(sds);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabelLabel = new javax.swing.JLabel();
        jTextFieldLabel = new javax.swing.JTextField();
        jLabelColor = new javax.swing.JLabel();
        colorSelectorPanel = new it.businesslogic.ireport.gui.sheet.ColorSelectorPanel();
        jButtonDefaultColor = new javax.swing.JButton();
        jLabelAlpha = new javax.swing.JLabel();
        jSpinnerAlpha = new javax.swing.JSpinner();
        jLabelRangeLow = new javax.swing.JLabel();
        jRTextExpressionLow = new it.businesslogic.ireport.gui.JRTextExpressionArea();
        jLabelRangeHigh = new javax.swing.JLabel();
        jRTextExpressionHigh = new it.businesslogic.ireport.gui.JRTextExpressionArea();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Time series");
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 250));
        jLabelLabel.setText("Label");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 4);
        jPanel1.add(jLabelLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel1.add(jTextFieldLabel, gridBagConstraints);

        jLabelColor.setText("Color");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 4);
        jPanel1.add(jLabelColor, gridBagConstraints);

        colorSelectorPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        colorSelectorPanel.setMinimumSize(new java.awt.Dimension(50, 10));
        colorSelectorPanel.setPreferredSize(new java.awt.Dimension(130, 22));
        colorSelectorPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorSelectorPanelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel1.add(colorSelectorPanel, gridBagConstraints);

        jButtonDefaultColor.setText("Use default color");
        jButtonDefaultColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDefaultColorActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        jPanel1.add(jButtonDefaultColor, gridBagConstraints);

        jLabelAlpha.setText("Alpha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 4);
        jPanel1.add(jLabelAlpha, gridBagConstraints);

        jSpinnerAlpha.setMinimumSize(new java.awt.Dimension(100, 18));
        jSpinnerAlpha.setPreferredSize(new java.awt.Dimension(130, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel1.add(jSpinnerAlpha, gridBagConstraints);

        jLabelRangeLow.setText("Data range low expression");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel1.add(jLabelRangeLow, gridBagConstraints);

        jRTextExpressionLow.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jRTextExpressionLow.setElectricScroll(0);
        jRTextExpressionLow.setMinimumSize(new java.awt.Dimension(10, 10));
        jRTextExpressionLow.setPreferredSize(new java.awt.Dimension(10, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel1.add(jRTextExpressionLow, gridBagConstraints);

        jLabelRangeHigh.setText("Data range high expression");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel1.add(jLabelRangeHigh, gridBagConstraints);

        jRTextExpressionHigh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jRTextExpressionHigh.setElectricScroll(0);
        jRTextExpressionHigh.setMinimumSize(new java.awt.Dimension(10, 10));
        jRTextExpressionHigh.setPreferredSize(new java.awt.Dimension(10, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel1.add(jRTextExpressionHigh, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        getContentPane().add(jSeparator1, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jPanel7, gridBagConstraints);

        jButtonOK.setMnemonic('o');
        jButtonOK.setText("OK");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel6.add(jButtonOK, gridBagConstraints);

        jButtonCancel.setMnemonic('c');
        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        jPanel6.add(jButtonCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel6, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDefaultColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDefaultColorActionPerformed

        colorSelectorPanel.setValue(null);
        
    }//GEN-LAST:event_jButtonDefaultColorActionPerformed

    private void colorSelectorPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorSelectorPanelActionPerformed

    }//GEN-LAST:event_colorSelectorPanelActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed

        MeterInterval tmpmi = getMeterInterval();
        if (tmpmi == null)
        {
            tmpmi = new MeterInterval();
        }
        
        tmpmi.setLabel( jTextFieldLabel.getText());
        tmpmi.setColor( (java.awt.Color)ColorSelectorPanel.parseColorString( ""+colorSelectorPanel.getValue()));
        
        tmpmi.setAlpha( ((SpinnerNumberModel)jSpinnerAlpha.getModel()).getNumber().doubleValue() );
        tmpmi.getDataRange().setLowExpression( jRTextExpressionLow.getText()  );
        tmpmi.getDataRange().setHighExpression( jRTextExpressionHigh.getText()  );
        
        setMeterInterval( tmpmi );
        
        this.setDialogResult(javax.swing.JOptionPane.OK_OPTION);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButtonOKActionPerformed
    
    
    public int getDialogResult() {
        return dialogResult;
    }

    public void setDialogResult(int dialogResult) {
        this.dialogResult = dialogResult;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private it.businesslogic.ireport.gui.sheet.ColorSelectorPanel colorSelectorPanel;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonDefaultColor;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JLabel jLabelAlpha;
    private javax.swing.JLabel jLabelColor;
    private javax.swing.JLabel jLabelLabel;
    private javax.swing.JLabel jLabelRangeHigh;
    private javax.swing.JLabel jLabelRangeLow;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private it.businesslogic.ireport.gui.JRTextExpressionArea jRTextExpressionHigh;
    private it.businesslogic.ireport.gui.JRTextExpressionArea jRTextExpressionLow;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinnerAlpha;
    private javax.swing.JTextField jTextFieldLabel;
    // End of variables declaration//GEN-END:variables
    
        public void applyI18n()
        {
                // Start autogenerated code ----------------------
                jButtonDefaultColor.setText(I18n.getString("meterIntervalDialog.buttonDefaultColor","Use default color"));
                // End autogenerated code ----------------------
            jButtonOK.setText( it.businesslogic.ireport.util.I18n.getString("ok","Ok"));
            jButtonCancel.setText( it.businesslogic.ireport.util.I18n.getString("cancel","Cancel"));
             
            jLabelLabel.setText( it.businesslogic.ireport.util.I18n.getString("meterinterval.label","Label"));
            jLabelColor.setText( it.businesslogic.ireport.util.I18n.getString("meterinterval.color","Color"));
            jLabelAlpha.setText( it.businesslogic.ireport.util.I18n.getString("meterinterval.alpha","Alpha"));
            jLabelRangeHigh.setText( it.businesslogic.ireport.util.I18n.getString("meterinterval.highexp","Data range high expression"));
            jLabelRangeLow.setText( it.businesslogic.ireport.util.I18n.getString("meterinterval.lowexp","Data range low expression"));
            
            setTitle(I18n.getString("meterIntervalDialog.title","Meter interval"));
            jButtonCancel.setMnemonic(I18n.getString("meterIntervalDialog.buttonCancelMnemonic","c").charAt(0));
            jButtonOK.setMnemonic(I18n.getString("meterIntervalDialog.buttonOKMnemonic","o").charAt(0));
                
            this.getRootPane().updateUI();
        }

    
    public MeterInterval getMeterInterval() {
        return meterInterval;
    }

    /*
     * The MeterInterval is stored and modified only when ok is pressed.
     * No further modifications are required to the original object like
     * all around iReport.
     *
     */
    public void setMeterInterval(MeterInterval meterInterval) {
        this.meterInterval = meterInterval;
        
        if (meterInterval != null)
        {
            jTextFieldLabel.setText( meterInterval.getLabel() );
            ((SpinnerNumberModel)jSpinnerAlpha.getModel()).setValue( new Double( meterInterval.getAlpha() ));
            if ( meterInterval.getColor() != null)
            {
                colorSelectorPanel.setValue(meterInterval.getColor()   );
            }
            jRTextExpressionLow.setText( meterInterval.getDataRange().getLowExpression() );
            jRTextExpressionHigh.setText( meterInterval.getDataRange().getHighExpression() );
        }
    }
    
    public static final int COMPONENT_NONE=0;
    public static final int COMPONENT_LOW_EXPRESSION=1;
    public static final int COMPONENT_HIGH_EXPRESSION=2;
        
    /**
     * This method set the focus on a specific component.
     * 
     * expressionInfo[0] can be something like:
     * COMPONENT_CATEGORY_EXPRESSION, COMPONENT_SERIES_EXPRESSION, COMPONENT_VALUE_EXPRESSION...
     *
     * If it is COMPONENT_HYPERLINK, other parameters are expected...
     * otherInfo is used here only for COMPONENT_HYPERLINK
     * otherInfo[0] = expression ID
     * otherInfo[1] = parameter #
     * otherInfo[2] = parameter expression ID
     */
    public void setFocusedExpression(Object[] expressionInfo)
    {
        if (expressionInfo == null) return;
        int expID = ((Integer)expressionInfo[0]).intValue();
        
        switch (expID)
        {
            case COMPONENT_LOW_EXPRESSION:
                Misc.selectTextAndFocusArea(jRTextExpressionLow);
                break;
            case COMPONENT_HIGH_EXPRESSION:
                Misc.selectTextAndFocusArea(jRTextExpressionHigh);
                break;
        }
    }
}