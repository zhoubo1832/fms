package prd.fms.controller;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import prd.fms.bean.DirNode;
import prd.fms.module.TreeNodeModule;

public class TreeNodeWillExpandController implements TreeWillExpandListener{

	@Override
	public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
		final TreePath path = event.getPath();
		
//		Object selectedComponent = path.getLastPathComponent();
//		if(selectedComponent instanceof String) {
//			return;
//		}
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		if(node == null) {
			return;
		}
		
		int childCount = node.getChildCount();
		if( childCount == 1) {
			DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) node.getChildAt(0);
			DirNode obj = (DirNode)childNode.getUserObject();
			if(obj.isDummyNode()) {
				DefaultTreeModel model = new DefaultTreeModel(node);
				model.removeNodeFromParent(childNode);
				
				TreeNodeModule.getDirNodes(node);
				//path.updateUI();
			}
		} else if(childCount > 1) {
			return;
		}
	}

	@Override
	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
		// TODO Auto-generated method stub
		
	}

}
