package prd.fms.view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.tree.TreePath;

import prd.fms.common.Command;
import prd.fms.common.CommandManager;
import prd.fms.common.ISubscriber;
import prd.fms.controller.ToolBarButtonController;
import prd.fms.controller.ViewListItemController;

/**
 * <p>Tool bar panel.</p>
 * 
 * @author zhoubo
 * 
 */
public class ToolbarPanel extends JPanel implements ISubscriber{

	private static final long serialVersionUID = 1L;

	/**
	 * <p>ToolbarPanel object itself.</p>
	 */
	public static ToolbarPanel instance;
	
	private JToolBar toolBar;
	private JButton backButton;
	private JButton forwardButton;
	private JButton renameButton;
	private JButton newFolderButton;
	private JButton copyButton;
	
	private JComboBox<String> viewList;

	private final String[] viewItems = {"Large Icons", "Details"};
	
	
	private static boolean backButtonClicked = false;
	private static boolean forwardButtonClicked = false;
	
	private ActionListener controller = new ToolBarButtonController();
	
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
		
		renameButton = new JButton(ViewConstants.TOOLBAR_RENAME_TEXT);
		renameButton.setEnabled(false);
		
		newFolderButton = new JButton(ViewConstants.TOOLBAR_NEWFOLDER_TEXT);
		newFolderButton.setEnabled(false);
		
		copyButton = new JButton(ViewConstants.TOOLBAR_COPY_TEXT);
		copyButton.setEnabled(false);
		
		viewList = new JComboBox<String>(viewItems);
		
		// add back button
		toolBar.add(backButton);
		// add forward button
		toolBar.add(forwardButton);
		
		toolBar.add(renameButton);
		toolBar.add(newFolderButton);
		toolBar.add(copyButton);
		toolBar.add(viewList);
		
		// add action listener for back button
		backButton.addActionListener(controller);
		// add action listener for forward button
		forwardButton.addActionListener(controller);
		renameButton.addActionListener(controller);
		newFolderButton.addActionListener(controller);
		copyButton.addActionListener(controller);
		viewList.addItemListener(new ViewListItemController());
		
		// add tool bar
		this.add(toolBar);
		// subscribe tree node selection change event
		MainTree.instance.getTreeNodeSelectionController().add(this);
	}
	
	public void setBackButtonEnable(boolean status) {
		this.backButton.setEnabled(status);
	}
	
	public void setForwardButtonEnable(boolean status) {
		this.forwardButton.setEnabled(status);
	}
	
	public boolean isBackButtonClicked() {
		return backButtonClicked;
	}
	
	public boolean isForwardButtonClicked() {
		return forwardButtonClicked;
	}

	public void setBackButtonClicked(boolean backButtonClicked) {
		ToolbarPanel.backButtonClicked = backButtonClicked;
	}
	
	public void setForwardButtonClicked(boolean forwardButtonClicked) {
		ToolbarPanel.forwardButtonClicked = forwardButtonClicked;
	}

	public JComboBox<String> getViewList() {
		return viewList;
	}
	
	public void setRenameButtonEnabled(Boolean enabled) {
		this.renameButton.setEnabled(enabled);
	}
	
	public void setNewfolderButtonEnabled(Boolean enabled) {
		this.newFolderButton.setEnabled(enabled);
	}
	
	public void setCopyButtonEnabled(Boolean enabled) {
		this.copyButton.setEnabled(enabled);
	}
	
	/**
	 * <p>When selected tree node is changed, update tool panel and command manager.</p>
	 * @param treePath  Tree path
	 */
	@Override
	public void update(TreePath treePath) {
		// Neither back button nor forward button was clicked
		if(!ToolbarPanel.instance.isBackButtonClicked() && !ToolbarPanel.instance.isForwardButtonClicked()) {
			// add history command to back command vector
			CommandManager.pushBackCommand(new Command(treePath));
			ToolbarPanel.instance.setBackButtonEnable(true);
			CommandManager.removeAllForwardCommand();
			ToolbarPanel.instance.setForwardButtonEnable(false);
			
		}
				
		ToolbarPanel.instance.setBackButtonClicked(false);
		ToolbarPanel.instance.setForwardButtonClicked(false);
		ToolbarPanel.instance.setCopyButtonEnabled(false);
	}
}
