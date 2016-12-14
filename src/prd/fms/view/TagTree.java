package prd.fms.view;

import javax.swing.JTree;

import prd.fms.executor.TagTreeMouseExecutor;
import prd.fms.model.TagModel;

public class TagTree extends JTree{
	
	public static boolean tagClicked = false;
	
	public static TagTree instance;
	
	public TagTree() {
		super(TagModel.getTagNodes());
		this.addMouseListener(new TagTreeMouseExecutor());
		instance = this;
	}
}
