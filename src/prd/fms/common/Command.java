package prd.fms.common;

import javax.swing.tree.TreePath;

public class Command {
	private TreePath treePath;
	
	public Command(TreePath treePath) {
		this.setTreePath(treePath);
	}

	public TreePath getTreePath() {
		return treePath;
	}

	public void setTreePath(TreePath treePath) {
		this.treePath = treePath;
	}

	@Override
	public boolean equals(Object obj) {
		TreePath tPath = (TreePath)obj;
		return this.treePath.equals(tPath);
	}
	
	
}
