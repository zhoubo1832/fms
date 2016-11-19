package prd.fms.controller;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import prd.fms.common.Command;
import prd.fms.common.CommandManager;
import prd.fms.module.TreeNodeModule;
import prd.fms.util.TreeUtil;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.ToolbarPanel;

/**
 * <p>Tree node's selection listener.</p>
 * 
 * @author zhoubo
 * 
 */
public class TreeNodeSelectionController implements TreeSelectionListener{

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath treePath = e.getPath();
		// display all folders and files in right panel
		TreeNodeModule.displayChildrenFiles(treePath);
		// get and add children folder nodes for current tree node
		TreeNodeModule.addChildrenDirNode(treePath);
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
		
		// display node's detailed information in information panel
		InfoBarPanel.instance.setNodeInfoLabel(TreeUtil.getPath(treePath));
	}
	
	
}
