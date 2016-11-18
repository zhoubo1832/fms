package prd.fms.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import prd.fms.util.TreeUtil;
import prd.fms.view.InfoBarPanel;

public class TreeNodeMouseController implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		JTree jtree = (JTree)e.getSource();
		TreePath tp = jtree.getPathForLocation(e.getX(), e.getY());
		String path = TreeUtil.getPath(tp);
		InfoBarPanel.instance.setNodeInfoLabel(path);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
