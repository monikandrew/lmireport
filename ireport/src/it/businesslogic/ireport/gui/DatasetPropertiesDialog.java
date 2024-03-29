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
 * PropertiesDialog.java
 * 
 * Created on 7 maggio 2003, 23.43
 *
 */

package it.businesslogic.ireport.gui;

import it.businesslogic.ireport.*;
import it.businesslogic.ireport.SubDataset;

import javax.swing.table.*;
import javax.swing.*;
import javax.swing.event.*;
import it.businesslogic.ireport.*;
import it.businesslogic.ireport.gui.table.CustomColumnControlButton;
import it.businesslogic.ireport.util.*;

import java.util.*;
import org.jdesktop.swingx.icon.ColumnControlIcon;
/**
 *
 * @author  Administrator
 */
public class DatasetPropertiesDialog extends javax.swing.JDialog {
    
    
    /** Creates new form ValuesDialog */
    public DatasetPropertiesDialog(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initAll();
    }
    public DatasetPropertiesDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initAll();
    }
    
    public void initAll()
    {
        initComponents();
        this.setSize(420, 250);

        jTableProperties.setColumnControl(new CustomColumnControlButton(jTableProperties, new ColumnControlIcon() ) );
        jTableProperties.setDragEnabled(false);
                
        DefaultListSelectionModel dlsm =  (DefaultListSelectionModel)this.jTableProperties.getSelectionModel();
        dlsm.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e)  {
                jTablePropertiesListSelectionValueChanged(e);
            }
        });     
            
	applyI18n();
        // Open in center...
        it.businesslogic.ireport.util.Misc.centerFrame(this);
        
        
        javax.swing.KeyStroke escape =  javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0, false);
        javax.swing.Action escapeAction = new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jButtonCloseActionPerformed(e);
            }
        };
       
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);


        //to make the default button ...
        this.getRootPane().setDefaultButton(this.jButtonClose);
        
    }
    
    
    public void jTablePropertiesListSelectionValueChanged(javax.swing.event.ListSelectionEvent e)
    {
         if (this.jTableProperties.getSelectedRowCount() > 0) {
            this.jButtonModifyProperty.setEnabled(true);
            this.jButtonDeleteProperty.setEnabled(true);
        }
        else {
            this.jButtonModifyProperty.setEnabled(false);
            this.jButtonDeleteProperty.setEnabled(false);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelFields = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableProperties = new it.businesslogic.ireport.gui.table.JDragTable();
        jPanelButtons2 = new javax.swing.JPanel();
        jButtonNewProperty = new javax.swing.JButton();
        jButtonModifyProperty = new javax.swing.JButton();
        jButtonDeleteProperty = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonClose = new javax.swing.JButton();

        setTitle("Values");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanelFields.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(32767, 32767));
        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseClicked(evt);
            }
        });

        jTableProperties.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProperties.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePropertiesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableProperties);

        jPanelFields.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanelButtons2.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanelButtons2.setMinimumSize(new java.awt.Dimension(100, 10));
        jPanelButtons2.setLayout(new java.awt.GridBagLayout());

        jButtonNewProperty.setText("New");
        jButtonNewProperty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewPropertyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 4, 0, 4);
        jPanelButtons2.add(jButtonNewProperty, gridBagConstraints);

        jButtonModifyProperty.setText("Modify");
        jButtonModifyProperty.setEnabled(false);
        jButtonModifyProperty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifyPropertyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 4);
        jPanelButtons2.add(jButtonModifyProperty, gridBagConstraints);

        jButtonDeleteProperty.setText("Delete");
        jButtonDeleteProperty.setEnabled(false);
        jButtonDeleteProperty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletePropertyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanelButtons2.add(jButtonDeleteProperty, gridBagConstraints);

        jPanel1.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 1.0;
        jPanelButtons2.add(jPanel1, gridBagConstraints);

        jButtonClose.setMnemonic('c');
        jButtonClose.setText("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanelButtons2.add(jButtonClose, gridBagConstraints);

        jPanelFields.add(jPanelButtons2, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanelFields, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jScrollPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseClicked
        // Add your handling code here:
    }//GEN-LAST:event_jScrollPane3MouseClicked

    private void jTablePropertiesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePropertiesMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == evt.BUTTON1 &&  jTableProperties.getSelectedRow() >=0 ) {
            jButtonModifyPropertyActionPerformed(new java.awt.event.ActionEvent( jButtonModifyProperty,0, ""));
        }
    }//GEN-LAST:event_jTablePropertiesMouseClicked

    private void jButtonNewPropertyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewPropertyActionPerformed
        java.awt.Frame parent = Misc.frameFromComponent(this);
        JRPropertyDialog jrpd = new JRPropertyDialog( parent, true);
        if (this.getDataset() instanceof Report)
        {
            jrpd.addExporterHints();
        }
        jrpd.setVisible(true);
        
        DefaultTableModel dtm = (DefaultTableModel)jTableProperties.getModel();
        
        if (jrpd.getDialogResult() == javax.swing.JOptionPane.OK_OPTION) {
            JRProperty property = jrpd.getProperty();
            dtm.addRow(new Object[]{property, property.getValue()});
            this.getDataset().addJRProperty( property );
            jTableProperties.updateUI();
        }
    }//GEN-LAST:event_jButtonNewPropertyActionPerformed

    private void jButtonModifyPropertyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifyPropertyActionPerformed
        
        int index = jTableProperties.getSelectedRow();
        index = jTableProperties.convertRowIndexToModel(index);
        DefaultTableModel dtm = (DefaultTableModel)jTableProperties.getModel();
        JRProperty property = (JRProperty)dtm.getValueAt( index, 0);
        java.awt.Frame parent = Misc.frameFromComponent(this);
        JRPropertyDialog jrpd = new JRPropertyDialog(parent, true);
        jrpd.setProperty( property );
        jrpd.setVisible(true);
        
        if (jrpd.getDialogResult() == javax.swing.JOptionPane.OK_OPTION) {
            property.setName( jrpd.getProperty().getName() );
            property.setValue( jrpd.getProperty().getValue() );
            dtm.setValueAt(property,  index, 0);
            dtm.setValueAt(property.getValue(), index, 1);
            jTableProperties.updateUI();
        }
    }//GEN-LAST:event_jButtonModifyPropertyActionPerformed

    private void jButtonDeletePropertyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletePropertyActionPerformed
        
        
        int[]  rows= jTableProperties.getSelectedRows();
        DefaultTableModel dtm = (DefaultTableModel)jTableProperties.getModel();
        for (int i=rows.length-1; i>=0; --i) {
            int index = jTableProperties.convertRowIndexToModel(rows[i]);
            JRProperty prop = (JRProperty)dtm.getValueAt(index, 0);
            this.getDataset().removeJRProperty(prop);
            dtm.removeRow( index );
        }
        
    }//GEN-LAST:event_jButtonDeletePropertyActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
    }//GEN-LAST:event_closeDialog
    
    public void setDataset(it.businesslogic.ireport.SubDataset dataset) {
        this.dataset = dataset;
        
        // Update all...
        if (dataset == null) 
        {
            setVisible(false);
            return;
        }
        
        if (isVisible())
        {
            this.setTitle(dataset.getName()+" properties...");
            updateProperties();
        }
    }
    
    public void updateProperties() {
        
        DefaultTableModel dtm = (DefaultTableModel)jTableProperties.getModel();
        dtm.setRowCount(0);
    
        Enumeration e = getDataset().getJRproperties().elements();
        while (e.hasMoreElements())
        {
            it.businesslogic.ireport.JRProperty property = (it.businesslogic.ireport.JRProperty)e.nextElement();
            Vector row = new Vector();
            row.addElement( property);
            row.addElement( property.getValue());
            
            dtm.addRow(row);           
        }    
    }
    
    
    public void applyI18n(){
                // Start autogenerated code ----------------------
                // End autogenerated code ----------------------
        jButtonNewProperty.setText(it.businesslogic.ireport.util.I18n.getString("new","New"));
        jButtonModifyProperty.setText(it.businesslogic.ireport.util.I18n.getString("modify","Modify"));
        jButtonDeleteProperty.setText(it.businesslogic.ireport.util.I18n.getString("delete","Delete"));
        jButtonClose.setText(it.businesslogic.ireport.util.I18n.getString("close","Close"));
        
        jTableProperties.getColumnModel().getColumn(0).setHeaderValue( I18n.getString("propertiesDialog.tablecolumn.name","Name") );
        jTableProperties.getColumnModel().getColumn(1).setHeaderValue( I18n.getString("propertiesDialog.tablecolumn.value","Value") );
        this.setTitle(I18n.getString("propertiesDialog.title","Properties"));
        jButtonClose.setMnemonic(I18n.getString("propertiesDialog.buttonCloseMnemonic","c").charAt(0));                
    }      
    public void languageChanged(LanguageChangedEvent evt) {
            
        this.applyI18n();
    }
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonDeleteProperty;
    private javax.swing.JButton jButtonModifyProperty;
    private javax.swing.JButton jButtonNewProperty;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelButtons2;
    private javax.swing.JPanel jPanelFields;
    private javax.swing.JScrollPane jScrollPane3;
    private it.businesslogic.ireport.gui.table.JDragTable jTableProperties;
    // End of variables declaration//GEN-END:variables

    private SubDataset dataset;    

    public void setVisible(boolean visible)
    {
        if (visible == isVisible()) return;
        super.setVisible(visible);
        if (visible == true) 
        {
            this.setDataset(this.dataset);
        }
    }

    public SubDataset getDataset() {
        return dataset;
    }

   
   }
