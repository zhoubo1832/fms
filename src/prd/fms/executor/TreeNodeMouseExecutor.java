package prd.fms.executor;

import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import prd.fms.controller.BaseMouseListener;
import prd.fms.util.CommonUtils;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.MainFrame;
import prd.fms.view.MainTree;
import prd.fms.view.TagDialog;
import prd.fms.view.ToolbarPanel;

public class TreeNodeMouseExecutor extends BaseMouseListener{

	
	@Override
	protected void executeClicked(MouseEvent e) {
		
		JTree jtree = (JTree)e.getSource();
		TreePath tp = jtree.getPathForLocation(e.getX(), e.getY());
		if(tp == null) {
			return;
		}
		
		if(e.getButton() == 1) {
			String path = CommonUtils.getPath(tp);
			// display node's detailed information in information panel
			InfoBarPanel.instance.setNodeInfoLabel(path);
			
			ToolbarPanel.instance.setRenameButtonEnabled(false);
			ToolbarPanel.instance.setNewfolderButtonEnabled(true);
		} else if(e.getButton() == 3) {
			MainTree.instance.setSelectionPath(tp);
			new TagDialog(MainFrame.instance, "Tags", true);
		}

	}

}