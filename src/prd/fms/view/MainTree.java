package prd.fms.view;

import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import prd.fms.executor.TreeNodeMouseExecutor;
import prd.fms.executor.TreeNodeTreeSelectionExecutor;
import prd.fms.executor.TreeNodeTreeWillExpandExecutor;
import prd.fms.model.TreeNodeModel;

/**
 * <p>File tree used to show folders.</p>
 * 
 * @author zhoubo
 * 
 */
public class MainTree extends JTree{
	
	private static final long serialVersionUID = 1L;
	
	private TreeNodeTreeSelectionExecutor treeNodeSelectionController;
	
	/**
	 * <p>MainTree object itself.</p>
	 */
	public static MainTree instance;
	
	public MainTree() {
		// get and display the first level nodes for lazy load
		super(TreeNodeModel.getRootNodes());
		
		// do not display root node
		this.setRootVisible(false);
		
		// show node handle for the first level nodes
		this.setShowsRootHandles(true);
		
		this.setCellRenderer(new MainTreeCellRender());
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		// add selected node change listener
		treeNodeSelectionController = new TreeNodeTreeSelectionExecutor();
		this.addTreeSelectionListener(treeNodeSelectionController);
		// add node expand listener
		this.addTreeWillExpandListener(new TreeNodeTreeWillExpandExecutor());
		// add mouse listener
		this.addMouseListener(new TreeNodeMouseExecutor());
		MainTree.instance = this;
	}
	
	public TreeNodeTreeSelectionExecutor getTreeNodeSelectionController() {
		return treeNodeSelectionController;
	}
}
