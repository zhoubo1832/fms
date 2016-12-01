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

import prd.fms.controller.NewfolderDialogController;

/**
 * <p>New folder dialog.</p>
 * 
 * @author zhoubo
 * 
 */
public class NewfolderDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField newNameTf;
	
	private JLabel msgLabel;

	private JButton okBtn;
	
	private NewfolderDialogController ctl;
	
	public NewfolderDialog(Frame parent, boolean modal) {
		super(parent, modal);
		this.setTitle(ViewConstants.NEWFOLDER_DIALOG_TITLE);
		this.setSize(ViewConstants.NEWFOLDER_DIALOG_WIDTH, ViewConstants.NEWFOLDER_DIALOG_HEIGHT);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int screenWidth = dim.width;
		int screenHeight = dim.height;
		this.setLocation(screenWidth/2 - this.getWidth()/2, screenHeight/2 - this.getHeight()/2);
		
		this.setLayout(new BorderLayout());
		
		ctl = new NewfolderDialogController(this);
		
		this.add(getTopPanel(), BorderLayout.NORTH);
		this.add(getBottomPanel(), BorderLayout.SOUTH);
		this.setAlwaysOnTop(true);
		this.requestFocus();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		newNameTf.getDocument().addDocumentListener(ctl);

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
		
		okBtn.addActionListener(ctl);
		cancelBtn.addActionListener(ctl);
		return panel;
	}
}
