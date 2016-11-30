package prd.fms.controller;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import prd.fms.util.CommonUtils;
import prd.fms.view.FilePanel;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.MainTree;
import prd.fms.view.RightPanel;
import prd.fms.view.ToolbarPanel;

public class ParentFilePanelMouseController implements MouseListener{

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
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
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
