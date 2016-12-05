package prd.fms.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import prd.fms.view.ProgressBarFrame;

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
		} else {
			if(oldFile.isDirectory()) {
				newFile.mkdirs();
				for(File file : oldFile.listFiles()) {
					if(file.isDirectory()) {
						copyDir(file.getPath(), newFile.getPath(), null);
						deleteDir(file.getPath());
					} else {
						copyFile(file.getPath(), newFile.getPath(), null);
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
		File file = new File(path + "\\" + newName);
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
	
//	public static int copyFiles(String[] srcPathArray, String desPath) {
//		for(String file : srcPathArray) {
//			if(new File(file).isDirectory()) {
//				copyDir(file, desPath);
//			} else {
//				copyFile(file, desPath);
//			}
//		}
//		return -1;
//	}
	
	public static void copyDir(String srcPath, String desPath, final ProgressBarFrame progressBar) {
		int pos = srcPath.lastIndexOf("\\");
		File file = new File(desPath + "\\" + srcPath.substring(pos+1));
//		if(file.exists()) {
//			return;
//		}
		
		file.mkdirs();
		
		if(progressBar != null) {
			try {
				SwingUtilities.invokeAndWait(new Runnable(){

					@Override
					public void run() {
						progressBar.updateProgress();							
					}
					
				});
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Copy folder:" + srcPath + " --> " + file.getPath());
		
		File[] files = new File(srcPath).listFiles();
		for(File f : files) {
			if(f.isDirectory()) {
				copyDir(f.getPath(), file.getPath(), progressBar);
			} else {
				copyFile(f.getPath(), file.getPath(), progressBar);
			}
		}
	}
	
	public static void copyFile(String srcPath, String desPath, final ProgressBarFrame progressBar) {
		int pos = srcPath.lastIndexOf("\\");
		File file = new File(desPath + "\\" + srcPath.substring(pos+1));
//		if(file.exists()) {
//			return;
//		}
		System.out.println("Copy file:" + srcPath + " --> " + file.getPath());
		
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
			
			if(progressBar != null) {
				try {
					SwingUtilities.invokeAndWait(new Runnable(){

						@Override
						public void run() {
							progressBar.updateProgress();							
						}
						
					});
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static String getDestPath(String file, String desPath) {
		int pos = file.lastIndexOf("\\");
		return desPath + "\\" + file.substring(pos+1);
	}
}
