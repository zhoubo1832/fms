package prd.fms.controller;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import prd.fms.model.TreeNodeModel;

/**
 * <p>Tree node's expand listener.</p>
 * 
 * @author zhoubo
 * 
 */
public class TreeNodeWillExpandController implements TreeWillExpandListener{

	@Override
	public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
		TreePath path = event.getPath();
		// get and add children folder nodes for current tree node
		TreeNodeModel.addChildrenDirNode(path);
	}

	@Override
	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
		// TODO Auto-generated method stub
		
	}

}
