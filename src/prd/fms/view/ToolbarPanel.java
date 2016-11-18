package prd.fms.view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import prd.fms.controller.BackForwardButtonController;

/**
 * <p>Tool bar panel.</p>
 * 
 * @author zhoubo
 * 
 */
public class ToolbarPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	/**
	 * <p>ToolbarPanel object itself.</p>
	 */
	public static ToolbarPanel instance;
	
	private JToolBar toolBar;
	private JButton backButton;
	private JButton forwardButton;
	
	private ActionListener controller = new BackForwardButtonController();
	
	public ToolbarPanel() {
		instance = this;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// create tool bar
		toolBar = new JToolBar();
		// create back button
		backButton = new JButton(ViewConstants.TOOLBAR_BACK_TEXT);
		// create forward button
		forwardButton = new JButton(ViewConstants.TOOLBAR_FORWARD_TEXT);
		
		backButton.setEnabled(false);
		forwardButton.setEnabled(false);
		
		// add back button
		toolBar.add(backButton);
		// add forward button
		toolBar.add(forwardButton);
		
		// add action listener for back button
		backButton.addActionListener(controller);
		// add action listener for forward button
		forwardButton.addActionListener(controller);
		
		// add tool bar
		this.add(toolBar);
	}
	
	public void setBackButtonEnable(boolean status) {
		this.backButton.setEnabled(status);
	}
	
	public void setForwardButtonEnable(boolean status) {
		this.forwardButton.setEnabled(status);
	}
}
