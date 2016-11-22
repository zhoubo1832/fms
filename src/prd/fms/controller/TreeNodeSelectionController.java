package prd.fms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import prd.fms.common.IPublisher;
import prd.fms.common.ISubscriber;
import prd.fms.module.TreeNodeModule;

/**
 * <p>Tree node's selection listener.</p>
 * 
 * @author zhoubo
 * 
 */
public class TreeNodeSelectionController implements TreeSelectionListener, IPublisher{

	private List<ISubscriber> listSub = new ArrayList<ISubscriber>();
	
	private TreePath treePath;
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		treePath = e.getPath();
		// display all folders and files in right panel
		TreeNodeModule.displayChildrenFiles(treePath);
		// get and add children folder nodes for current tree node
		TreeNodeModule.addChildrenDirNode(treePath);
		// publish value changed message to all subscribers
		send();
	}

	@Override
	public void add(ISubscriber sub) {
		listSub.add(sub);
	}

	@Override
	public void send() {
		for(ISubscriber sub: listSub) {
			sub.update(treePath);
		}
	}
	
	
}
