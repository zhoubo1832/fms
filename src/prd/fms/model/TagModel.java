package prd.fms.model;

import java.util.ArrayList;
import java.util.List;

import prd.fms.bean.TagBean;
import prd.fms.util.DbUtils;

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
}
