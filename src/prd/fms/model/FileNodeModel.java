package prd.fms.model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import prd.fms.bean.DirNode;
import prd.fms.view.MainTree;

public class FileNodeModel {
	public static void openFileNode(File file) {
		// folder was double clicked
		if(file.isDirectory()) {
			// get and select current tree node
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) MainTree.instance.getSelectionPath().getLastPathComponent();
			Enumeration enumeration = selectedNode.breadthFirstEnumeration();
			while(enumeration.hasMoreElements()) {

				DefaultMutableTreeNode node = (DefaultMutableTreeNode)enumeration.nextElement(); 
		        if(file.getPath().equals(((DirNode)node.getUserObject()).getPath())) {
		        	
		        	TreeNode[] nodes = ((DefaultTreeModel) MainTree.instance.getModel()).getPathToRoot(node);
		        	TreePath treePath = new TreePath(nodes);
		        	MainTree.instance.scrollPathToVisible(treePath);
					MainTree.instance.setSelectionPath(treePath);
		        } 
		    } 
			
		} else {
		// file was double clicked
			Desktop desk = Desktop.getDesktop();
			try {
				// open file
				desk.open(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void refresh() {
		TreePath treePath = MainTree.instance.getSelectionPath();
		if(treePath != null) {
			TreeNodeModel.displayChildrenFiles(treePath);
		}
	}
}
