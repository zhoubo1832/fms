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
import prd.fms.module.TreeNodeModule;
import prd.fms.view.FilePanel;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.MainTree;
import prd.fms.view.RightPanel;

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
			// folder was double clicked
			if(file.isDirectory()) {
				// display all folders and files in right panel
//				File[] files = TreeNodeModule.getAllNodes(file.getPath());
//				RightPanel rightPanel = RightPanel.instance;
//				rightPanel.removeAll();
//				rightPanel.show(files);
				
				// get and select current tree node
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) MainTree.instance.getSelectionPath().getLastPathComponent();
				Enumeration enumeration = selectedNode.breadthFirstEnumeration();
				while(enumeration.hasMoreElements()) {

					DefaultMutableTreeNode node = (DefaultMutableTreeNode)enumeration.nextElement(); 
			        if(file.getPath().equals(((DirNode)node.getUserObject()).getPath())) {
			        	
			        	TreeNode[] nodes = ((DefaultTreeModel) MainTree.instance.getModel()).getPathToRoot(node);
			        	TreePath treePath = new TreePath(nodes);
			        	MainTree.instance.scrollPathToVisible(treePath);
						MainTree.instance.setSelectionPath(treePath);
			        } 
			    } 
				
			} else {
			// file was double clicked
				Desktop desk = Desktop.getDesktop();
				try {
					// open file
					desk.open(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
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
