package prd.fms.controller;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import prd.fms.bean.DirNode;
import prd.fms.model.FileNodeModel;
import prd.fms.model.TreeNodeModel;
import prd.fms.view.FilePanel;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.MainTree;
import prd.fms.view.RightPanel;
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
		} else {
		// one click	
			FilePanel panel = (FilePanel)e.getSource();
			// request focus for file panel, and focusGained method will be called automatically
			panel.requestFocus();
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
