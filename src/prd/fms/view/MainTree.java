package prd.fms.view;

import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import prd.fms.controller.TreeNodeSelectionController;
import prd.fms.controller.TreeNodeWillExpandController;
import prd.fms.module.TreeNodeModule;

public class MainTree extends JTree{
	
	public static MainTree instance;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainTree() {
		super(TreeNodeModule.getRootNodes());
		this.setRootVisible(true);
		this.setShowsRootHandles(false);
		
		this.setCellRenderer(new MainTreeCellRender());
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		this.addTreeSelectionListener(new TreeNodeSelectionController());
		this.addTreeWillExpandListener(new TreeNodeWillExpandController());
		MainTree.instance = this;
	}
}
