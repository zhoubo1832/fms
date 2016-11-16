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
import prd.fms.view.MainTree;
import prd.fms.view.RightPanel;

public class FilePanelMouseController implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		int n = e.getClickCount();
		if(n == 2){
			FilePanel panel = (FilePanel)e.getSource();
			File file = panel.getFile();
			if(file.isDirectory()) {
				File[] files = TreeNodeModule.getAllNodes(file.getPath());
				RightPanel rightPanel = RightPanel.instance;
				rightPanel.removeAll();
				rightPanel.show(files);
				
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
				Desktop desk = Desktop.getDesktop();
				try {
					desk.open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else {
			JPanel panel = (JPanel)e.getSource();
			panel.requestFocus();
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
