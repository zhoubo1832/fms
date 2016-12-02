package prd.fms.executor;

import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import prd.fms.controller.BaseMouseListener;
import prd.fms.util.CommonUtils;
import prd.fms.view.FilePanel;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.MainTree;
import prd.fms.view.RightPanel;
import prd.fms.view.ToolbarPanel;

public class ParentFilePanelMouseExecutor extends BaseMouseListener{

	@Override
	protected void executePressed(MouseEvent e) {
		JPanel panel = RightPanel.instance.getParentFilePanel();
		
		for( Component p : panel.getComponents()) {
			FilePanel fPanel = (FilePanel) p;
			fPanel.setBorder(null);
			fPanel.setBackground(null);
		}
		
		ToolbarPanel.instance.setCopyButtonEnabled(false);
		
		String path = CommonUtils.getPath(MainTree.instance.getSelectionPath());
		InfoBarPanel.instance.setNodeInfoLabel(path);		
	}

}

