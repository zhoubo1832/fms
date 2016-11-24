package prd.fms.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.tree.TreePath;

import prd.fms.model.TreeNodeModel;
import prd.fms.view.MainTree;

public class ViewListItemController implements ItemListener{

	@Override
	public void itemStateChanged(ItemEvent e) {
		TreePath treePath = MainTree.instance.getSelectionPath();
		if(treePath != null) {
			TreeNodeModel.displayChildrenFiles(treePath);
		}
	}
}
