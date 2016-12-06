package prd.fms.common;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import prd.fms.model.FileSystemModel;
import prd.fms.view.ProgressBarFrame;

public class FileSystemModelProxy {
	
	private static ThreadLocal<ProgressBarFrame> progressBar = new ThreadLocal<ProgressBarFrame>();
	
	public static void copyDir(String srcPath, String desPath) {
		updateProgress();
		FileSystemModel.copyDir(srcPath, desPath);
		
	}
	
	public static void copyFile(String srcPath, String desPath) {
		FileSystemModel.copyFile(srcPath, desPath);
		updateProgress();
	}
	
	public static void setProgressBar(ProgressBarFrame progressBar) {
		FileSystemModelProxy.progressBar.set(progressBar);
	}
	
	private static void updateProgress() {
		final ProgressBarFrame bar = progressBar.get();
		try {
			SwingUtilities.invokeAndWait(new Runnable(){

				@Override
				public void run() {
					bar.updateProgress();							
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
