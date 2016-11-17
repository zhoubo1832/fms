package prd.fms.common;

import java.util.Vector;

import javax.swing.tree.TreePath;

import prd.fms.module.TreeNodeModule;
import prd.fms.view.MainTree;

public class CommandManager {
	private static Vector<Command> backCommand = new Vector<Command>();
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
	
	public static void back() {
		setBackButtonClicked(true);
		
		Command command = backCommand.get(backCommand.size() - 2);
		
		TreePath treePath = command.getTreePath();
		TreeNodeModule.displayChildrenFiles(treePath);
		TreeNodeModule.addChildrenDirNode(treePath);
		
		MainTree.instance.scrollPathToVisible(treePath);
		MainTree.instance.setSelectionPath(treePath);
		
		Command removedCommand = backCommand.remove(backCommand.size() - 1);
		forwardCommand.add(removedCommand);
		
	}
	
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
