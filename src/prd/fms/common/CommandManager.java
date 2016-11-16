package prd.fms.common;

import java.util.EmptyStackException;
import java.util.Stack;

import javax.swing.tree.TreePath;

import prd.fms.module.TreeNodeModule;
import prd.fms.view.MainTree;

public class CommandManager {
	private static Stack<Command> backCommand = new Stack<Command>();
	private static Stack<Command> forwardCommand = new Stack<Command>();
	
	public static void pushCommand(Command command) {
		backCommand.push(command);
	}
	
	public static void back() {
		Command command = null;
		try{
			command = backCommand.pop();
		} catch (EmptyStackException e) {
			return;
		}
		
		TreePath treePath = command.getTreePath();
		TreeNodeModule.displayChildrenFiles(treePath);
		TreeNodeModule.addChildrenDirNode(treePath);
		
		MainTree.instance.scrollPathToVisible(treePath);
		MainTree.instance.setSelectionPath(treePath);
	}
	
	public static void forward() {
		
	}
}
