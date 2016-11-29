package prd.fms.model;

import java.io.File;

/**
 * <p>File system model.</p>
 * 
 * @author zhoubo
 * 
 */
public class FileSystemModel {

	/**
	 * <p>Rename folder or file.</p>
	 * @param path  Old path
	 * @param newName  New name
	 * @return 0:success 1:exist -1:fail
	 */
	public static int rename(String path, String newName) {
		File oldFile = new File(path);
		String tmpStr = path.substring(0, path.lastIndexOf("\\") + 1);
		File newFile = new File(tmpStr + newName);
		if(newFile.exists()) {
			return 1;
		}
		if(oldFile.renameTo(newFile)){
			return 0;
		}
		return -1;
	}
	
	/**
	 * <p>New folder.</p>
	 * @param path  Old path
	 * @param newName  New name
	 * @return 0:success 1:exist -1:fail
	 */
	public static int newFolder(String path, String newName) {
		File file = new File(path + "\\" + newName);
		if( file.exists() && file.isDirectory() ) {
			return 1;
		}
		
		if(file.mkdirs()) {
			return 0;
		}
		return -1;
	}
}
