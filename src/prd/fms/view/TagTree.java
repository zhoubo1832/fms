package prd.fms.view;

import javax.swing.JTree;

import prd.fms.executor.TagTreeMouseExecutor;
import prd.fms.model.TagModel;

public class TagTree extends JTree{

	public TagTree() {
		super(TagModel.getTagNodes());
		this.addMouseListener(new TagTreeMouseExecutor());
	}
}
