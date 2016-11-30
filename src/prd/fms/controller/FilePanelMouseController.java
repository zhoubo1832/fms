package prd.fms.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import prd.fms.common.SelectedFileList;
import prd.fms.model.FileNodeModel;
import prd.fms.view.FilePanel;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.ToolbarPanel;

/**
 * <p>File panel's mouse listener.</p>
 * 
 * @author zhoubo
 * 
 */
public class FilePanelMouseController implements MouseListener{
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int n = e.getClickCount();
		// double click
		if(n == 2){
			FilePanel panel = (FilePanel)e.getSource();
			File file = panel.getFile();
			FileNodeModel.openFileNode(file);
			
			ToolbarPanel.instance.setRenameButtonEnabled(false);
			ToolbarPanel.instance.setCopyButtonEnabled(false);
			
			SelectedFileList.getInstance().clear();
		} else {
		// one click	
			FilePanel panel = (FilePanel)e.getSource();
			boolean flgControl = FilePanelKeyController.controlKeyPressed;
			if(!flgControl) {
				Container container = panel.getParent();
				for( Component p : container.getComponents()) {
					if(p != panel) {
						((JComponent) p).setBorder(null);
						p.setBackground(null);
					}
				}
//				for(FilePanel p : SelectedFileList.getInstance().getPanelList()) {
//					p.setBorder(null);
//					p.setBackground(null);
//				}
//				SelectedFileList.getInstance().clear();
			}
//			SelectedFileList.getInstance().addPanel(panel);
			// request focus for file panel, and focusGained method will be called automatically
			panel.requestFocus();
			panel.setBackground(new Color(219,243,146));
			panel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
			
			ToolbarPanel.instance.setRenameButtonEnabled(true);
			ToolbarPanel.instance.setCopyButtonEnabled(true);
			
			// display file detailed information in information panel
			InfoBarPanel.instance.setFileInfoLabel(panel.getFile().getPath());
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		JPanel panel = (JPanel)e.getSource();
		if(!panel.isFocusOwner()) {
			panel.setBackground(new Color(232,255,232));
			panel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JPanel panel = (JPanel)e.getSource();
		if(!panel.isFocusOwner()) {
			panel.setBorder(null);
			panel.setBackground(null);
		}
	}
}
