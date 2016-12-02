package prd.fms.model;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import prd.fms.bean.DirNode;
import prd.fms.view.MainTree;
import prd.fms.view.RightPanel;

/**
 * <p>Tree node model.</p>
 * 
 * @author zhoubo
 * 
 */
public class TreeNodeModel {

	/**
	 * <p>Get the first level nodes.</p>
	 * @return Top node
	 */
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
	
	/**
	 * <p>Get all folders and files.</p>
	 * @param path  File path
	 * @return File array
	 */
	public static File[] getAllNodes(String path) {
				
		File file = new File(path);
		File[] fileArray = file.listFiles();

		return fileArray;
	}
	
	/**
	 * <p>Get all folder nodes.</p>
	 * @param node  Node
	 * @return Node with all children folder nodes
	 */
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
	
	/**
	 * <p>Whether folder has sub folders.</p>
	 * @param path  File path
	 * @return true: has not  false:has
	 */
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
	
	/**
	 * <p>Get and add children folder nodes for current tree node</p>
	 * @param path  Tree path
	 */
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
				// remove dummy node
				model.removeNodeFromParent(childNode);
				// add all folder nodes
				TreeNodeModel.getDirNodes(node);
			}
		} else if(childCount > 1) {
			
		}
	}
	
	public static void refresh() {
		TreePath path = MainTree.instance.getSelectionPath();
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		if(node == null) {
			return;
		}

		// add all folder nodes
		DefaultTreeModel model = (DefaultTreeModel)MainTree.instance.getModel();
		node.removeAllChildren();
		
		TreeNodeModel.getDirNodes(node);
		model.reload(node);
	}
	
	/**
	 * <p>Display all folders and files in right panel</p>
	 * @param treePath  Tree path
	 */
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
		File[] files = TreeNodeModel.getAllNodes(path);
				
		RightPanel.instance.show(files);

	}
}
