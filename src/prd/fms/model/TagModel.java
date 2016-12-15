package prd.fms.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import prd.fms.bean.DirNode;
import prd.fms.bean.TagBean;
import prd.fms.util.DbUtils;
import prd.fms.view.LeftPanel;
import prd.fms.view.TagTree;

public class TagModel {

	public static List<Object> getFileTags(String filePath) {
		List<Object> tagBeanList = new ArrayList<Object>();
		DbUtils db = new DbUtils();
		List<String> fileTagList = db.selectFileTags(filePath);
		List<String> allTagList = db.selectAllTags();
		for(String s1 : allTagList) {
			TagBean bean = new TagBean();
			bean.setFilePath(filePath);
			bean.setTagName(s1);
			if(fileTagList.contains(s1)) {
				bean.setSelected(true);
			}
			tagBeanList.add(bean);
		}
		return tagBeanList;
	}
	
	public static List<String> getAllTags() {
		return new DbUtils().selectAllTags();
	}
	
	public static DefaultMutableTreeNode getTagNodes() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Tags");
		List<String> tagList = new DbUtils().selectAllTags();
		for(String s : tagList) {
			top.add(new DefaultMutableTreeNode(s));
		}
		
		return top;
	}
	
	public static List<String> getTagFiles(String tagName) {
		return new DbUtils().selectTagFiles(tagName);
	}
	
	public static void deleteTaggedFile(String fileName) {
		new DbUtils().deleteTaggedFile(fileName);
	}
	
	public static void renameTaggedFile(String oldName, String newName) {
		new DbUtils().updateTaggedFile(oldName, newName);
	}
	
	public static void updateFileTags(String filePath, List<String> list) {
		new DbUtils().updateFileTags(filePath, list);
	}
	
	public static void refreshTagTree() {
		TagTree tagTree = LeftPanel.instance.getTagTree();
		DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)tagTree.getModel().getRoot();
		rootNode.removeAllChildren();
		List<String> list = new DbUtils().selectAllTags();
		for(String s : list) {
			rootNode.add(new DefaultMutableTreeNode(s));
		}
		tagTree.updateUI();
	}
}
