package prd.fms.executor;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.tree.TreePath;

import prd.fms.controller.BaseTreeWillExpandListener;
import prd.fms.model.TreeNodeModel;

public class TreeNodeTreeWillExpandExecutor extends BaseTreeWillExpandListener{

	@Override
	protected void executeExpand(TreeExpansionEvent event) {
		TreePath path = event.getPath();
		// get and add children folder nodes for current tree node
		TreeNodeModel.addChildrenDirNode(path);		
	}

}
