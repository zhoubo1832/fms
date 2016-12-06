package prd.fms.bean;

import java.io.File;

/**
 * <p>Dir node definition.</p>
 * 
 * @author zhoubo
 * 
 */
public class DirNode {

	public static String ROOT_NODE = "ROOT";
	public static String DUMMY_NODE = "DUMMY";
	public static String NORMAL_NODE = "NORMAL";
	
	private String name = "";
	
	private String path = "";
	
	private boolean isEmpty = true;
	
	private String type = NORMAL_NODE;
	
	public DirNode() {
		
	}
	
	public DirNode(String path, boolean isEmpty) {
		this.path = path;
		this.isEmpty = isEmpty;
		
		this.name = path;
		if(path.endsWith(File.separator)) {
			name = path.substring(0, path.length()-1);
		} else {
			int pos = path.lastIndexOf(File.separator);
			name = path.substring(pos+1);
		}
	}
	
	public DirNode(String type) {
		this.type = type;
		this.path = type;
		this.name = type;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isRootNode() {
		return ROOT_NODE.equals(this.type);
	}
	
	public boolean isDummyNode() {
		return DUMMY_NODE.equals(this.type);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
