package prd.fms.executor;

import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import prd.fms.bean.TagBean;
import prd.fms.controller.BaseMouseListener;
import prd.fms.model.TagModel;
import prd.fms.model.TreeNodeModel;
import prd.fms.util.CommonUtils;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.MainFrame;
import prd.fms.view.MainTree;
import prd.fms.view.TagDialog;
import prd.fms.view.TagTree;
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
			TagTree.tagClicked = false;
			
			String path = CommonUtils.getPath(tp);
			
			TreePath currentTreePath = MainTree.instance.getSelectionPath();
			String currentPath = CommonUtils.getPath(currentTreePath);
			if(path.equals(currentPath)) {
				// display all folders and files in right panel
				TreeNodeModel.displayChildrenFiles(currentTreePath);
			}
			
			// display node's detailed information in information panel
			InfoBarPanel.instance.setNodeInfoLabel(path);
			
			ToolbarPanel.instance.setRenameButtonEnabled(false);
			ToolbarPanel.instance.setNewfolderButtonEnabled(true);
		} else if(e.getButton() == 3) {
			String filePath = CommonUtils.getPath(tp);
			List<Object> list = TagModel.getFileTags(filePath);
			MainTree.instance.setSelectionPath(tp);
			new TagDialog(MainFrame.instance, "Tags", true, filePath, list);
		}

	}

}