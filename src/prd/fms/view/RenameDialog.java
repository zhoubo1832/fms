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

import prd.fms.controller.RenameDialogController;

public class RenameDialog extends JDialog{
	
	private JTextField newNameTf = new JTextField(20);
	
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
		panel.add(newNameTf);
		
		//newNameTf.addInputMethodListener(InputMethodListener);
		return panel;
	}
	
	private JPanel getBottomPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton okBtn = new JButton(ViewConstants.COMMON_BUTTON_OK);
		JButton cancelBtn = new JButton(ViewConstants.COMMON_BUTTON_CANCEL);
		panel.add(okBtn);
		panel.add(cancelBtn);
		
		RenameDialogController ctl = new RenameDialogController(this);
		okBtn.addActionListener(ctl);
		cancelBtn.addActionListener(ctl);
		return panel;
	}
}
