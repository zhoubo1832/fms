package prd.fms.util;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import prd.fms.bean.DirNode;
import prd.fms.view.MainTree;

/**
 * <p>Tree utilization definition.</p>
 * 
 * @author zhoubo
 * 
 */
public class CommonUtils {
	
	private static HashMap<String, Icon> bigMap = new HashMap<String, Icon>();
	private static HashMap<String, Icon> smallMap = new HashMap<String, Icon>();
	

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
	 * <p>Get current path.</p>
	 * @return String Current path
	 */
	public static String getCurrentPath() {
		String path = null;
		TreePath treePath = MainTree.instance.getSelectionPath();
		if(treePath != null) {
			path = getPath(treePath);
		}
		return path;
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
		String suffix = null;
		
		String filePath = f.getPath();
		if(f.isFile()) {
			int pos = filePath.lastIndexOf(".");
			if(pos != -1) {
				suffix = filePath.substring(pos);
				Icon icon = smallMap.get(suffix);
				if(icon != null) {
					return icon; 
				}
			}
		}
		
	    if (f != null && f.exists()) {  
	        FileSystemView fsv = FileSystemView.getFileSystemView();
	        if(fsv != null) {
	        	Icon imgIcon = fsv.getSystemIcon(f);
	        	if(f.isFile() && imgIcon != null) {
            		smallMap.put(suffix, imgIcon);
            	}
            	return imgIcon;
	        } else {
	        	System.out.println("no image:" + f.getAbsolutePath());
	        }
	    }  
	    return null;  
	}
	
	public static Icon getBigIcon(File f) {
		String suffix = null;
		
		String filePath = f.getPath();
		if(f.isFile()) {
			int pos = filePath.lastIndexOf(".");
			if(pos != -1) {
				suffix = filePath.substring(pos);
				Icon icon = bigMap.get(suffix);
				if(icon != null) {
					return icon; 
				}
			}
		}
		
        if (f!=null && f.exists()) {  
            try {  
                sun.awt.shell.ShellFolder sf = sun.awt.shell.ShellFolder.getShellFolder(f);
                Image img = sf.getIcon(true);
                if(img != null) {
                	Icon imgIcon = new ImageIcon(sf.getIcon(true));
                	if(f.isFile() && imgIcon != null) {
                		bigMap.put(suffix, imgIcon);
                	}
                	return imgIcon;
                }
                System.out.println("no image:" + f.getAbsolutePath());
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
	
	public static File[] convertToFileArray(List<String> fileList) {
		int numFiles = fileList.size();
		if(numFiles == 0 ) {
			return null;
		}
		Iterator<String> it = fileList.iterator();
		while(it.hasNext()) {
			if(new File(it.next()).exists() == false) {
				it.remove();
			}
		}
		
		numFiles = fileList.size();
		if(numFiles == 0 ) {
			return null;
		}
		
		File[] files = new File[numFiles];
		for(int i=0; i< numFiles; i++) {
			files[i] = new File(fileList.get(i));
		}
		return files;
	}
}
