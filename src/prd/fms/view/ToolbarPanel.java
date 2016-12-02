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
import prd.fms.controller.BackButtonActionExecutor;
import prd.fms.controller.CopyButtonActionExecutor;
import prd.fms.controller.ForwardButtonActionExecutor;
import prd.fms.controller.NewfolderButtonActionExecutor;
import prd.fms.controller.PasteButtonActionExecutor;
import prd.fms.controller.RenameButtonActionExecutor;
import prd.fms.controller.ViewlistItemExecutor;

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
	private JButton newfolderButton;
	private JButton copyButton;
	private JButton pasteButton;
	
	private JComboBox<String> viewList;

	private final String[] viewItems = {"Large Icons", "Details"};
	
	
	private static boolean backButtonClicked = false;
	private static boolean forwardButtonClicked = false;
	
	private ActionListener renameController = new RenameButtonActionExecutor();
	private ActionListener newfolderController = new NewfolderButtonActionExecutor();
	private ActionListener backController = new BackButtonActionExecutor();
	private ActionListener forwardController = new ForwardButtonActionExecutor();
	private ActionListener copyController = new CopyButtonActionExecutor();
	private ActionListener pasteController = new PasteButtonActionExecutor();
	
	public ToolbarPanel() {
		instance = this;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.createWidget();
		this.setEnable();
		this.addListener();
		this.addWidget();
		
		// subscribe tree node selection change event
		MainTree.instance.getTreeNodeSelectionController().add(this);
	}
	
	private void createWidget() {
		// create tool bar
		toolBar = new JToolBar();
		// create back button
		backButton = new JButton(ViewConstants.TOOLBAR_BACK_TEXT);
		// create forward button
		forwardButton = new JButton(ViewConstants.TOOLBAR_FORWARD_TEXT);
		renameButton = new JButton(ViewConstants.TOOLBAR_RENAME_TEXT);
		newfolderButton = new JButton(ViewConstants.TOOLBAR_NEWFOLDER_TEXT);
		copyButton = new JButton(ViewConstants.TOOLBAR_COPY_TEXT);
		pasteButton = new JButton(ViewConstants.TOOLBAR_PASTE_TEXT);
		viewList = new JComboBox<String>(viewItems);
		
	}
	
	private void setEnable() {
		backButton.setEnabled(false);
		forwardButton.setEnabled(false);
		renameButton.setEnabled(false);
		newfolderButton.setEnabled(false);
		copyButton.setEnabled(false);
		pasteButton.setEnabled(false);
		
	}
	
	private void addListener() {
		// add action listener for back button
		backButton.addActionListener(backController);
		// add action listener for forward button
		forwardButton.addActionListener(forwardController);
		renameButton.addActionListener(renameController);
		newfolderButton.addActionListener(newfolderController);
		copyButton.addActionListener(copyController);
		pasteButton.addActionListener(pasteController);
		viewList.addItemListener(new ViewlistItemExecutor());
	}
	
	private void addWidget() {
		// add back button
		toolBar.add(backButton);
		// add forward button
		toolBar.add(forwardButton);
		
		toolBar.add(renameButton);
		toolBar.add(newfolderButton);
		toolBar.add(copyButton);
		toolBar.add(pasteButton);
		toolBar.add(viewList);
		// add tool bar
		this.add(toolBar);
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
		this.newfolderButton.setEnabled(enabled);
	}
	
	public void setCopyButtonEnabled(Boolean enabled) {
		this.copyButton.setEnabled(enabled);
	}
	
	public void setPasteButtonEnabled(Boolean enabled) {
		this.pasteButton.setEnabled(enabled);
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
