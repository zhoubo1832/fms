package prd.fms.module;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import prd.fms.bean.DirNode;
import prd.fms.view.RightPanel;

public class TreeNodeModule {

	public static DefaultMutableTreeNode getRootNodes() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(new DirNode(DirNode.ROOT_NODE));
		File[] files = File.listRoots();
		for(File f : files) {
			DirNode dirNode = new DirNode(f.getPath(), isEmptyDir(f.getPath()));
			
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(dirNode);
			if(!dirNode.isEmpty()) {
				DefaultMutableTreeNode dummy = new DefaultMutableTreeNode(new DirNode(DirNode.DUMMY_NODE));
				node.add(dummy);
			}
			top.add(node);
		}
		
		return top;
	}
	
	public static File[] getAllNodes(String path) {
				
		File file = new File(path);
		File[] fileArray = file.listFiles();

		return fileArray;
	}
	
	
	public static DefaultMutableTreeNode getDirNodes(DefaultMutableTreeNode node) {
		DirNode dirNode = (DirNode)node.getUserObject();
		
		File file = new File(dirNode.getPath());
		File[] fileArray = file.listFiles();
		
		if(fileArray == null) {
			return null;
		}
		
		for(File f : fileArray) {
			if(f.isDirectory()) {
				
				DirNode newNode = new DirNode(f.getPath(), isEmptyDir(f.getPath()));
				
				DefaultMutableTreeNode addNode = new DefaultMutableTreeNode(newNode);
				
				if(!newNode.isEmpty()) {
					DefaultMutableTreeNode dummy = new DefaultMutableTreeNode(new DirNode(DirNode.DUMMY_NODE));
					addNode.add(dummy);
				}
				
				node.add(addNode);
			}
		}
		return node;
	}
	
	public static boolean isEmptyDir(String path) {
		
		File file = new File(path);
		File[] fileArray = file.listFiles();
		if(fileArray == null) {
			return true;
		}
		for(File f : fileArray) {
			if(f.isDirectory()) {
				return false;
			}
		}
		return true;
	}
	
	public static void addChildrenDirNode(TreePath path) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		if(node == null) {
			return;
		}
		
		DirNode dirNode = (DirNode)node.getUserObject();
		if(dirNode.isRootNode()) {
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
			}
		} else if(childCount > 1) {
			return;
		}
	}
	
	
	public static void displayChildrenFiles(TreePath treePath) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
		if(node == null) {
			return;
		}

		DirNode dirNode = (DirNode)node.getUserObject();
		if(dirNode.isRootNode()) {
			return;
		}
		String path = dirNode.getPath();
		File[] files = TreeNodeModule.getAllNodes(path);
				
		RightPanel.instance.show(files);
	}
}
