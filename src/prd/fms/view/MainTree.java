package prd.fms.view;

import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import prd.fms.controller.TreeNodeMouseController;
import prd.fms.controller.TreeNodeSelectionController;
import prd.fms.controller.TreeNodeWillExpandController;
import prd.fms.module.TreeNodeModule;

/**
 * <p>File tree used to show folders.</p>
 * 
 * @author zhoubo
 * 
 */
public class MainTree extends JTree{
	
	/**
	 * <p>MainTree object itself.</p>
	 */
	public static MainTree instance;
	
	private static final long serialVersionUID = 1L;

	public MainTree() {
		// get and display the first level nodes for lazy load
		super(TreeNodeModule.getRootNodes());
		
		// do not display root node
		this.setRootVisible(false);
		
		// show node handle for the first level nodes
		this.setShowsRootHandles(true);
		
		this.setCellRenderer(new MainTreeCellRender());
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		// add selected node change listener
		this.addTreeSelectionListener(new TreeNodeSelectionController());
		// add node expand listener
		this.addTreeWillExpandListener(new TreeNodeWillExpandController());
		// add mouse listener
		this.addMouseListener(new TreeNodeMouseController());
		MainTree.instance = this;
	}
}
