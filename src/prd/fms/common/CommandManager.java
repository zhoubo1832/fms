package prd.fms.common;

import java.util.Vector;

import javax.swing.tree.TreePath;

import prd.fms.module.TreeNodeModule;
import prd.fms.view.MainTree;

public class CommandManager {
	private static Vector<Command> backCommand = new Vector<Command>();
	private static Vector<Command> forwardCommand = new Vector<Command>();

	private static boolean backButtonClicked = false;
	
	public static boolean isCanBack() {
		return (backCommand.size() > 1);
	}
	
	public static void pushCommand(Command command) {
		backCommand.addElement(command);
	}
	
	public static void back() {
		setBackButtonClicked(true);
		
		Command command = null;
		command = backCommand.get(backCommand.size() - 2);
		
		TreePath treePath = command.getTreePath();
		TreeNodeModule.displayChildrenFiles(treePath);
		TreeNodeModule.addChildrenDirNode(treePath);
		
		MainTree.instance.scrollPathToVisible(treePath);
		MainTree.instance.setSelectionPath(treePath);
		
		backCommand.remove(backCommand.size() - 1);
	}
	
	public static void forward() {
		
	}

	public static boolean isBackButtonClicked() {
		return backButtonClicked;
	}

	public static void setBackButtonClicked(boolean backButtonClicked) {
		CommandManager.backButtonClicked = backButtonClicked;
	}
}
