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

public class TreeNodeSelectionController implements TreeSelectionListener{

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath treePath = e.getPath();
		
		TreeNodeModule.displayChildrenFiles(treePath);
		
		TreeNodeModule.addChildrenDirNode(treePath);
		
		if(!CommandManager.isBackButtonClicked() && !CommandManager.isForwardButtonClicked()) {
			CommandManager.pushBackCommand(new Command(treePath));
			
			ToolbarPanel.instance.setBackButtonEnable(true);
			CommandManager.removeAllForwardCommand();
			ToolbarPanel.instance.setForwardButtonEnable(false);
			
		}
				
		CommandManager.setBackButtonClicked(false);
		CommandManager.setForwardButtonClicked(false);
		
		InfoBarPanel.instance.setNodeInfoLabel(TreeUtil.getPath(treePath));
	}
	
	
}
