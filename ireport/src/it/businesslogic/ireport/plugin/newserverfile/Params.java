/*
 * Params.java
 *
 * Created on 2009年10月16日, 上午9:16
 */

package it.businesslogic.ireport.plugin.newserverfile;

import java.util.ArrayList;

import javax.swing.text.Document;

import com.chinacreator.ireport.AddedOperator;
import com.chinacreator.ireport.IreportConstant;
import com.chinacreator.ireport.IreportUtil;
import com.chinacreator.ireport.component.DialogFactory;

/**
 * 
 * @author Administrator
 */
public class Params extends javax.swing.JDialog {

	/** Creates new form Params */
	JavaBeanDatasourceSet father = null;

	public Params(JavaBeanDatasourceSet father, java.awt.Dialog parent,
			boolean modal) {
		super(parent, modal);
		this.father = father;
		father.paramObj = null;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setText("是否需要输入差数？若需要请在下面输入框中输入参数");

		jLabel2.setText("每一行填写一个参数，参数顺序对应方法参数顺序，若需要输入null，请填值");

		jLabel3.setText("为null，若不需要输入参数请点击取消");

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		jButton1.setText("取消");
		jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton1MouseClicked(evt);
			}
		});

		jButton2.setText("确定");
		jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton2MouseClicked(evt);
			}
		});

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								jLabel2,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								409,
																								Short.MAX_VALUE)
																						.add(
																								jLabel1,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								409,
																								Short.MAX_VALUE)
																						.add(
																								jLabel3,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								409,
																								Short.MAX_VALUE)
																						.add(
																								jScrollPane1,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								409,
																								Short.MAX_VALUE))
																		.addContainerGap())
														.add(
																org.jdesktop.layout.GroupLayout.TRAILING,
																layout
																		.createSequentialGroup()
																		.add(
																				jButton2)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jButton1)
																		.add(
																				19,
																				19,
																				19)))));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.add(jLabel1)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(jLabel2)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(jLabel3)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jScrollPane1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.UNRELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jButton1).add(
																jButton2))
										.addContainerGap(
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:确定
		ArrayList<Object> list = new ArrayList<Object>();
		
		if (IreportUtil.isBlank(jTextArea1.getText())) {
			DialogFactory.showErrorMessageDialog(this, "未设定任何参数", "错误");;
			return;
		}
		try {
			System.out.println("-------:"+jTextArea1.getText());
			Document doc = jTextArea1.getDocument();
			for (int i = 0; i < jTextArea1.getLineCount(); i++) {
				
				int start = jTextArea1.getLineStartOffset(i);
				int end = jTextArea1.getLineEndOffset(i);
				String param = doc.getText(start, end - start);
				if ("".equals(param)) {
					break;
				}
				if ("null".equals(param)) {
					list.add(null);
				} else {
					list.add(param);
				}
			}
			father.paramObj = list.toArray();
			this.setVisible(false);
			this.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			AddedOperator.log("设置参数错误:" + e.getMessage(),
					IreportConstant.ERROR_);
			DialogFactory.showErrorMessageDialog(this, "设置参数错误:" + e.getMessage(), "内部错误");
		}

	}

	private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:取消
		father.paramObj = null;
		this.setVisible(false);
		this.dispose();
	}

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	// End of variables declaration

}
