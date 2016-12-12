package prd.fms.thread;

import prd.fms.model.FileSystemModel;

public class SearchFileThread extends Thread{

	private String key;
	private String currentPath;
	public static volatile boolean stopFlag = false;
	
	public SearchFileThread(String key, String currentPath) {
		this.key = key;
		this.currentPath = currentPath;
	}
	
	@Override
	public void run() {
		stopFlag = false;
		System.out.println("start search...");
		FileSystemModel.searchFile(key, currentPath);
		System.out.println("end search...");
	}
}
