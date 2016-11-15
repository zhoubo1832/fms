package prd.fms.module;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

import prd.fms.bean.DirNode;

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
}
