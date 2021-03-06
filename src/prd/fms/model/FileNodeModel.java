package prd.fms.model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import prd.fms.bean.DirNode;
import prd.fms.util.CommonUtils;
import prd.fms.view.MainTree;
import prd.fms.view.TagTree;

/**
 * <p>File node model.</p>
 * 
 * @author zhoubo
 * 
 */
public class FileNodeModel {
	
	/**
	 * <p>Open folder or file.</p>
	 * @param file  File/Folder
	 */
	public static void openFileNode(File file) {
		// folder was double clicked
		if(file.isDirectory()) {
			TagTree.tagClicked = false;
			
			// get and select current tree node
			String path = file.getPath();
			TreePath currentTreePath = MainTree.instance.getSelectionPath();
			if(currentTreePath != null) {
				String currentPath = CommonUtils.getPath(currentTreePath);
				if(path.equals(currentPath)) {
					// display all folders and files in right panel
					TreeNodeModel.displayChildrenFiles(currentTreePath);
					return;
				}
			}
			
			TreePath selectedTreePath = new TreePath(MainTree.instance.getModel().getRoot());
			String selectedPath = "";
			
//			String partPath = path.substring(selectedPath.length());
			StringTokenizer token = new StringTokenizer(file.getPath(),File.separator,false);
			while(token.hasMoreElements()) {
				String dirName = (String)token.nextElement();
				if("".equals(selectedPath)) {
					selectedPath = dirName + File.separator;
				} else {
					if(selectedPath.endsWith(File.separator)) {
						selectedPath = selectedPath + dirName;
					} else {
						selectedPath = selectedPath + File.separator + dirName;
					}
				}
				selectedTreePath = getTreePath(selectedPath, selectedTreePath);
				if(selectedTreePath != null && token.hasMoreElements()) {
					TreeNodeModel.addChildrenDirNode(selectedTreePath);
				}
			}
			
			if(selectedTreePath != null) {
				MainTree.instance.scrollPathToVisible(selectedTreePath);
				MainTree.instance.setSelectionPath(selectedTreePath);
				return;
			}
			
			
			
//			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) MainTree.instance.getSelectionPath().getLastPathComponent();
//			Enumeration enumeration = selectedNode.breadthFirstEnumeration();
//			while(enumeration.hasMoreElements()) {
//
//				DefaultMutableTreeNode node = (DefaultMutableTreeNode)enumeration.nextElement(); 
//		        if(file.getPath().equals(((DirNode)node.getUserObject()).getPath())) {
//		        	
//		        	TreeNode[] nodes = ((DefaultTreeModel) MainTree.instance.getModel()).getPathToRoot(node);
//		        	TreePath treePath = new TreePath(nodes);
//		        	MainTree.instance.scrollPathToVisible(treePath);
//					MainTree.instance.setSelectionPath(treePath);
//					return;
//		        } 
//		    } 
			
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
	
	/**
	 * <p>Refresh right panel.</p>
	 */
	public static void refresh() {
		TreePath treePath = MainTree.instance.getSelectionPath();
		if(treePath != null) {
			TreeNodeModel.displayChildrenFiles(treePath);
		}
	}
	
	private static TreePath getTreePath(String path, TreePath parentTreePath) {
		TreePath sonTreePath = null;
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)parentTreePath.getLastPathComponent();
		Enumeration enumeration = selectedNode.breadthFirstEnumeration();
		while(enumeration.hasMoreElements()) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode)enumeration.nextElement(); 
	        if(path.equals(((DirNode)node.getUserObject()).getPath())) {
	        	
	        	TreeNode[] nodes = ((DefaultTreeModel) MainTree.instance.getModel()).getPathToRoot(node);
	        	sonTreePath = new TreePath(nodes);
	        	break;
	        } 
	    } 
		return sonTreePath;
	}
}
