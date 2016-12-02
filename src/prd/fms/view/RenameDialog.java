package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import prd.fms.executor.RenameDialogActionExecutor;
import prd.fms.executor.RenameDialogDocumentExecutor;

/**
 * <p>Rename dialog.</p>
 * 
 * @author zhoubo
 * 
 */
public class RenameDialog extends CommonDialog{
	
	private static final long serialVersionUID = 1L;

	private JTextField newNameTf;
	
	private JLabel msgLabel;

	private JButton okBtn;
	
	public JButton getOkBtn() {
		return okBtn;
	}

	private String oldText;
	
	public String getOldText() {
		return oldText;
	}

	private RenameDialogActionExecutor ctlAction;
	
	private RenameDialogDocumentExecutor ctlDocument;
	
	public RenameDialog(Frame parent, boolean modal) {
		super(parent, ViewConstants.RENAME_DIALOG_TITLE, modal);
		
	}
	
	public JLabel getMsgLabel() {
		return msgLabel;
	}
	
	public JTextField getNewNameTf() {
		return newNameTf;
	}
	
	private JPanel getTopPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel(ViewConstants.RENAME_DIALOG_LABEL));
		
		newNameTf = new JTextField(20);
		oldText = (new File(InfoBarPanel.selectedPath)).getName();
		newNameTf.setText(oldText);
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
		this.setSize(ViewConstants.RENAME_DIALOG_WIDTH, ViewConstants.RENAME_DIALOG_HEIGHT);
		
//		this.setScreenCenter();
		
		this.setLayout(new BorderLayout());
		
		ctlAction = new RenameDialogActionExecutor(this);
		ctlDocument = new RenameDialogDocumentExecutor(this);
		
		this.add(getTopPanel(), BorderLayout.NORTH);
		this.add(getBottomPanel(), BorderLayout.SOUTH);		
	}
}
