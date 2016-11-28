package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import prd.fms.controller.RenameDialogController;

/**
 * <p>Rename dialog.</p>
 * 
 * @author zhoubo
 * 
 */
public class RenameDialog extends JDialog{
	
	private static final long serialVersionUID = 1L;

	private JTextField newNameTf;
	
	private JButton okBtn;
	
	public JButton getOkBtn() {
		return okBtn;
	}

	private String oldText;
	
	public String getOldText() {
		return oldText;
	}

	private RenameDialogController ctl;
	
	public RenameDialog(Frame parent, boolean modal) {
		super(parent, modal);
		this.setTitle(ViewConstants.RENAME_DIALOG_TITLE);
		this.setSize(ViewConstants.RENAME_DIALOG_WIDTH, ViewConstants.RENAME_DIALOG_HEIGHT);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int screenWidth = dim.width;
		int screenHeight = dim.height;
		this.setLocation(screenWidth/2 - this.getWidth()/2, screenHeight/2 - this.getHeight()/2);
		
		this.setLayout(new BorderLayout());
		
		ctl = new RenameDialogController(this);
		
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
	
	private JPanel getTopPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel(ViewConstants.RENAME_DIALOG_LABEL));
		
		newNameTf = new JTextField(20);
		oldText = (new File(InfoBarPanel.selectedPath)).getName();
		newNameTf.setText(oldText);
		newNameTf.getDocument().addDocumentListener(ctl);

		panel.add(newNameTf);
		
		return panel;
	}
	
	private JPanel getBottomPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		okBtn = new JButton(ViewConstants.COMMON_BUTTON_OK);
		okBtn.setEnabled(false);
		
		JButton cancelBtn = new JButton(ViewConstants.COMMON_BUTTON_CANCEL);
		panel.add(okBtn);
		panel.add(cancelBtn);
		
		okBtn.addActionListener(ctl);
		cancelBtn.addActionListener(ctl);
		return panel;
	}
}
