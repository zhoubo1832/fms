package prd.fms.controller;

import java.io.File;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import prd.fms.bean.DirNode;
import prd.fms.module.TreeNodeModule;
import prd.fms.view.RightPanel;

public class TreeNodeSelectionController implements TreeSelectionListener{

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath treePath = e.getPath();
		
		TreeNodeModule.displayChildrenFiles(treePath);
		
		TreeNodeModule.addChildrenDirNode(treePath);
	}
	
	
}
