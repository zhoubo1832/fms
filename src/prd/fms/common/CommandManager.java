package prd.fms.common;

import java.util.Vector;

import javax.swing.tree.TreePath;

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
		
		Command command = backCommand.get(backCommand.size() - 2);
		
		TreePath treePath = command.getTreePath();
		
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
		
		Command command = forwardCommand.lastElement();
		
		TreePath treePath = command.getTreePath();

		// expand all nodes for current tree path
		MainTree.instance.scrollPathToVisible(treePath);
		// select node for current tree path
		MainTree.instance.setSelectionPath(treePath);
		
		Command removedCommand = forwardCommand.remove(forwardCommand.size() - 1);
		backCommand.add(removedCommand);
	}

	public static void removeAllForwardCommand() {
		forwardCommand.removeAllElements();
	}

}
