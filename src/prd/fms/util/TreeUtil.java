package prd.fms.util;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import prd.fms.bean.DirNode;

public class TreeUtil {

	public static String getPath(TreePath treePath) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
		if(node == null) {
			return null;
		}

		DirNode dirNode = (DirNode)node.getUserObject();
		if(dirNode.isRootNode()) {
			return null;
		}
		return dirNode.getPath();
	}
}
