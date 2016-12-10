package prd.fms.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import prd.fms.common.FileSystemModelProxy;
import prd.fms.view.RightPanel;

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
		String tmpStr = path.substring(0, path.lastIndexOf(File.separator) + 1);
		File newFile = new File(tmpStr + newName);
		if(newFile.exists()) {
			return 1;
		}
		
		if(oldFile.renameTo(newFile)){
			return 0;
		} else {
			if(oldFile.isDirectory()) {
				newFile.mkdirs();
				for(File file : oldFile.listFiles()) {
					if(file.isDirectory()) {
						copyDirForRename(file.getPath(), newFile.getPath());
						deleteDir(file.getPath());
					} else {
						copyFile(file.getPath(), newFile.getPath());
						file.delete();
					}
				}
				
				oldFile.delete();
				return 0;
			}

		}
		return -1;
	}
	
	public static void deleteDir(String path) {
		File file = new File(path);
		for(File f : file.listFiles()) {
			if(f.isDirectory()) {
				deleteDir(f.getPath());
			} else {
				f.delete();
			}
		}
		file.delete();
	}
	
	/**
	 * <p>New folder.</p>
	 * @param path  Old path
	 * @param newName  New name
	 * @return 0:success 1:exist -1:fail
	 */
	public static int newFolder(String path, String newName) {
		File file = new File(path + File.separator + newName);
		if( file.exists() ) {
			return 1;
		}
		
		if(file.mkdirs()) {
			return 0;
		}
		return -1;
	}
	
	public static int fileCount(String[] srcPathArray) {
		int fileCount = 0;
		for(String file : srcPathArray) {
			if(new File(file).isDirectory()) {
				fileCount++;
				File[] files = new File(file).listFiles();
				String[] strFiles = new String[files.length];
				int i=0;
				for(File f : files) {
					strFiles[i] = f.getPath();
					i++;
				}
				fileCount += fileCount(strFiles);
			} else {
				fileCount++;
			}
		}
		return fileCount;
	}
	
	public static void copyDir(String srcPath, String desPath) throws InterruptedException {
		int pos = srcPath.lastIndexOf(File.separator);
		File file = new File(desPath + File.separator + srcPath.substring(pos+1));
		
		file.mkdirs();
		
		File[] files = new File(srcPath).listFiles();
		for(File f : files) {
			if(f.isDirectory()) {
				FileSystemModelProxy.copyDir(f.getPath(), file.getPath());
			} else {
				FileSystemModelProxy.copyFile(f.getPath(), file.getPath());
			}
		}
	}
	
	public static void copyDirForRename(String srcPath, String desPath) {
		int pos = srcPath.lastIndexOf(File.separator);
		File file = new File(desPath + File.separator + srcPath.substring(pos+1));
		
		file.mkdirs();
		
		File[] files = new File(srcPath).listFiles();
		for(File f : files) {
			if(f.isDirectory()) {
				copyDirForRename(f.getPath(), file.getPath());
			} else {
				copyFile(f.getPath(), file.getPath());
			}
		}
	}
	
	public static void copyFile(String srcPath, String desPath) {
		int pos = srcPath.lastIndexOf(File.separator);
		File file = new File(desPath + File.separator + srcPath.substring(pos+1));

		FileInputStream inputStream = null;
		byte[] buffer = new byte[1024];
		int readNum = 0;
		
		FileOutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(srcPath);
			outputStream = new FileOutputStream(file);
			while((readNum = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, readNum);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static String getDestPath(String file, String desPath) {
		int pos = file.lastIndexOf(File.separator);
		return desPath + File.separator + file.substring(pos+1);
	}
	
	public static List<File> searchFile(String key, String path) {
		List<File> list = new ArrayList<File>();
		searchFile(key, path, list);

		return list;
	}
	
	public static List<File> searchFile(String key, String path, List<File> list) {
		System.out.println("path:" + path);
		File root = new File(path);
		File[] listFile = root.listFiles();
		if(listFile != null) {	
			for(final File f : listFile) {
				String filePath = f.getPath();
				if(filePath.substring(path.length()).contains(key)) {
					list.add(f);
					SwingUtilities.invokeLater(new Runnable(){

						@Override
						public void run() {
							RightPanel.instance.addFilePanel(f);
						}
						
					});
				}
				
				if(f.isDirectory()) {
					searchFile(key, filePath, list);
				}
			}
		}
		return list;
	}

}
