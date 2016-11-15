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
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
		if(node == null) {
			return;
		}

		DirNode dirNode = (DirNode)node.getUserObject();
		String path = dirNode.getPath();
		File[] files = TreeNodeModule.getAllNodes(path);
		if(files.length == 0 ) {
			return;
		}
		
		RightPanel rightPanel = RightPanel.instance;
		rightPanel.removeAll();
				
		rightPanel.show(files);
		
		
	}
	
	
}
