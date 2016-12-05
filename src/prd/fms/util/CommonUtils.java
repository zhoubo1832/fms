package prd.fms.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;
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
	
	public static Icon getSmallIcon(File f) {  
	    if (f != null && f.exists()) {  
	        FileSystemView fsv = FileSystemView.getFileSystemView();  
	        return fsv.getSystemIcon(f);  
	    }  
	    return null;  
	}
	
	public static Icon getBigIcon(File f) {  
        if (f!=null && f.exists()) {  
            try {  
                sun.awt.shell.ShellFolder sf = sun.awt.shell.ShellFolder.getShellFolder(f);  
                return new ImageIcon(sf.getIcon(true));  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }
	
	public static void setScreenCenter(Window win) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int screenWidth = dim.width;
		int screenHeight = dim.height;
		win.setLocation(screenWidth/2 - win.getWidth()/2, screenHeight/2 - win.getHeight()/2);
	}
}
