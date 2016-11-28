package prd.fms.util;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import prd.fms.bean.DirNode;

/**
 * <p>Tree utilization definition.</p>
 * 
 * @author zhoubo
 * 
 */
public class CommonUtils {

	/**
	 * <p>Get file path string from treePath.</p>
	 * @param treePath  Tree path
	 * @return File path string
	 */
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
	
	/**
	 * <p>Get size string.</p>
	 * @param size  File size(byte)
	 * @return size string(byte/KB/MB/GB)
	 */
	public static String getSize(long size) {
		if(size < 1024) {
			return size + " byte";
		} else if(size < 1024*1024) {
			return size/1024 + " KB";
		} else if(size < 1024*1024*1024) {
			return size/1024/1024 + " MB";
		} else if(size < (1024l*1024l*1024l*1024l)) {
			return size/1024/1024/1024 + " GB";
		} else {
			return size + " byte";
		}
	}
}
