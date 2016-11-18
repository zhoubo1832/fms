package prd.fms.common;

import java.util.Vector;

import javax.swing.tree.TreePath;

import prd.fms.module.TreeNodeModule;
import prd.fms.view.MainTree;

/**
 * <p>Command manager used to back and forward operation.</p>
 * 
 * @author zhoubo
 * 
 */
public class CommandManager {
	
	/**
	 * <p>Back commands vector.</p>
	 */
	private static Vector<Command> backCommand = new Vector<Command>();
	
	/**
	 * <p>Forward commands vector.</p>
	 */
	private static Vector<Command> forwardCommand = new Vector<Command>();

	private static boolean backButtonClicked = false;
	private static boolean forwardButtonClicked = false;
	
	public static boolean isCanBack() {
		return (backCommand.size() > 1);
	}
	
	public static boolean isCanForward() {
		return (forwardCommand.size() > 0);
	}
	
	public static void pushBackCommand(Command command) {
		backCommand.addElement(command);
	}
	
	/**
	 * <p>Back operation.</p>
	 */
	public static void back() {
		setBackButtonClicked(true);
		
		Command command = backCommand.get(backCommand.size() - 2);
		
		TreePath treePath = command.getTreePath();
		// display all folders and files in right panel
		TreeNodeModule.displayChildrenFiles(treePath);
		// get and add children folder nodes for current tree node
		TreeNodeModule.addChildrenDirNode(treePath);
		
		// expand all nodes for current tree path
		MainTree.instance.scrollPathToVisible(treePath);
		// select node for current tree path
		MainTree.instance.setSelectionPath(treePath);
		
		// remove current command from back vector
		Command removedCommand = backCommand.remove(backCommand.size() - 1);
		// add current command into forward vector
		forwardCommand.add(removedCommand);
		
	}
	
	/**
	 * <p>Forward operation.</p>
	 */
	public static void forward() {
		setForwardButtonClicked(true);
		
		Command command = forwardCommand.lastElement();
		
		TreePath treePath = command.getTreePath();
		TreeNodeModule.displayChildrenFiles(treePath);
		TreeNodeModule.addChildrenDirNode(treePath);
		
		MainTree.instance.scrollPathToVisible(treePath);
		MainTree.instance.setSelectionPath(treePath);
		
		Command removedCommand = forwardCommand.remove(forwardCommand.size() - 1);
		backCommand.add(removedCommand);
	}

	public static void removeAllForwardCommand() {
		forwardCommand.removeAllElements();
	}
	
	public static boolean isBackButtonClicked() {
		return backButtonClicked;
	}
	
	public static boolean isForwardButtonClicked() {
		return forwardButtonClicked;
	}

	public static void setBackButtonClicked(boolean backButtonClicked) {
		CommandManager.backButtonClicked = backButtonClicked;
	}
	
	public static void setForwardButtonClicked(boolean forwardButtonClicked) {
		CommandManager.forwardButtonClicked = forwardButtonClicked;
	}
}
