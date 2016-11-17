package prd.fms.controller;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import prd.fms.common.Command;
import prd.fms.common.CommandManager;
import prd.fms.module.TreeNodeModule;
import prd.fms.view.ToolbarPanel;

public class TreeNodeSelectionController implements TreeSelectionListener{

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath treePath = e.getPath();
		
		TreeNodeModule.displayChildrenFiles(treePath);
		
		TreeNodeModule.addChildrenDirNode(treePath);
		
		if(!CommandManager.isBackButtonClicked()) {
			CommandManager.pushCommand(new Command(treePath));
			ToolbarPanel.instance.setBackButtonEnable(true);
		}
		CommandManager.setBackButtonClicked(false);
	}
	
	
}
