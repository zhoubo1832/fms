package prd.fms.executor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreePath;

import prd.fms.common.IPublisher;
import prd.fms.common.ISubscriber;
import prd.fms.controller.BaseTreeSelectionListener;
import prd.fms.model.TreeNodeModel;
import prd.fms.view.TagTree;

public class TreeNodeTreeSelectionExecutor extends BaseTreeSelectionListener implements IPublisher{

	private List<ISubscriber> listSub = new ArrayList<ISubscriber>();
	
	private TreePath treePath;
	
	@Override
	protected void execute(TreeSelectionEvent e) {
		TagTree.tagClicked = false;
		
		treePath = e.getPath();
		// display all folders and files in right panel
		TreeNodeModel.displayChildrenFiles(treePath);
		// get and add children folder nodes for current tree node
		TreeNodeModel.addChildrenDirNode(treePath);
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
