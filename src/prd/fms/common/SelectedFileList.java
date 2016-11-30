package prd.fms.common;

import java.util.ArrayList;

public class SelectedFileList {
	
	private static SelectedFileList instance;
	
	private ArrayList<String> pathList = new ArrayList<String>();

	private SelectedFileList() {
		
	}
	
	public static SelectedFileList getInstance() {
		if(instance != null) {
			return instance;
		} else {
			instance = new SelectedFileList();
			return instance;
		}
	}
	
	public ArrayList<String> getPathList() {
		return pathList;
	}
	
	public void clear() {
		pathList.clear();
	}
	
	public void addPath(String path) {
		pathList.add(path);
	}
	
	public boolean isEmpty() {
		return pathList.isEmpty();
	}
}
