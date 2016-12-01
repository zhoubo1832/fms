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
		if( file.exists() ) {
			return 1;
		}
		
		if(file.mkdirs()) {
			return 0;
		}
		return -1;
	}
	
	public static int copyFiles(String[] srcPathArray, String desPath) {
		for(String file : srcPathArray) {
			if(new File(file).isDirectory()) {
				copyDir(file, desPath);
			} else {
				copyFile(file, desPath);
			}
		}
		return -1;
	}
	
	public static void copyDir(String srcPath, String desPath) {
		int pos = srcPath.lastIndexOf("\\");
		File file = new File(desPath + "\\" + srcPath.substring(pos+1));
//		if(file.exists()) {
//			return;
//		}
		
//		file.mkdirs();
		System.out.println("Copy folder:" + srcPath + " --> " + file.getPath());
		
		File[] files = new File(srcPath).listFiles();
		for(File f : files) {
			if(f.isDirectory()) {
				copyDir(f.getPath(), file.getPath());
			} else {
				copyFile(f.getPath(), file.getPath());
			}
		}
	}
	
	public static void copyFile(String srcPath, String desPath) {
		int pos = srcPath.lastIndexOf("\\");
		File file = new File(desPath + "\\" + srcPath.substring(pos+1));
//		if(file.exists()) {
//			return;
//		}
		System.out.println("Copy file:" + srcPath + " --> " + file.getPath());
	}
	
	public static String getDestPath(String file, String desPath) {
		int pos = file.lastIndexOf("\\");
		return desPath + "\\" + file.substring(pos+1);
	}
}
