package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import prd.fms.controller.NewfolderDialogActionExecutor;
import prd.fms.controller.NewfolderDialogController;
import prd.fms.controller.NewfolderDialogDocumentExecutor;

/**
 * <p>New folder dialog.</p>
 * 
 * @author zhoubo
 * 
 */
public class NewfolderDialog extends CommonDialog {

	private static final long serialVersionUID = 1L;

	private JTextField newNameTf;
	
	private JLabel msgLabel;

	private JButton okBtn;
	
	private NewfolderDialogActionExecutor ctlAction;
	
	private NewfolderDialogDocumentExecutor ctlDocument;
	
	public NewfolderDialog(Frame parent, boolean modal) {
		super(parent, ViewConstants.NEWFOLDER_DIALOG_TITLE, modal);
		
	}
	
	public JTextField getNewNameTf() {
		return newNameTf;
	}
	
	public JLabel getMsgLabel() {
		return msgLabel;
	}
	
	public JButton getOkBtn() {
		return okBtn;
	}
	
	private JPanel getTopPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel(ViewConstants.NEWFOLDER_DIALOG_LABEL));
		
		newNameTf = new JTextField(20);
		
		newNameTf.getDocument().addDocumentListener(ctlDocument);

		panel.add(newNameTf);
		
		return panel;
	}
	
	private JPanel getBottomPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		msgLabel = new JLabel();
		
		okBtn = new JButton(ViewConstants.COMMON_BUTTON_OK);
		okBtn.setEnabled(false);
		
		JButton cancelBtn = new JButton(ViewConstants.COMMON_BUTTON_CANCEL);
		
		panel.add(msgLabel);
		panel.add(okBtn);
		panel.add(cancelBtn);
		
		okBtn.addActionListener(ctlAction);
		cancelBtn.addActionListener(ctlAction);
		return panel;
	}

	@Override
	protected void setUI() {
		this.setSize(ViewConstants.NEWFOLDER_DIALOG_WIDTH, ViewConstants.NEWFOLDER_DIALOG_HEIGHT);
		
//		this.setScreenCenter();
		
		this.setLayout(new BorderLayout());
		
		ctlAction = new NewfolderDialogActionExecutor(this);
		ctlDocument = new NewfolderDialogDocumentExecutor(this);
		
		this.add(getTopPanel(), BorderLayout.NORTH);
		this.add(getBottomPanel(), BorderLayout.SOUTH);		
	}
}
