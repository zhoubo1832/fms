package prd.fms.view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import prd.fms.controller.BackForwardButtonController;

public class ToolbarPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ToolbarPanel instance;
	
	private JToolBar toolBar;
	private JButton backButton;
	private JButton forwardButton;
	private ActionListener controller = new BackForwardButtonController();
	
	public ToolbarPanel() {
		instance = this;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar = new JToolBar();
		backButton = new JButton(ViewConstants.TOOLBAR_BACK_TEXT);
		forwardButton = new JButton(ViewConstants.TOOLBAR_FORWARD_TEXT);
		
		backButton.setEnabled(false);
		forwardButton.setEnabled(false);
		toolBar.add(backButton);
		toolBar.add(forwardButton);
		
		backButton.addActionListener(controller);
		forwardButton.addActionListener(controller);
		
		this.add(toolBar);
	}
	
	public void setBackButtonEnable(boolean status) {
		this.backButton.setEnabled(status);
	}
}
