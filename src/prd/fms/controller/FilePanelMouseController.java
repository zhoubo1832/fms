package prd.fms.controller;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import prd.fms.view.FilePanel;

public class FilePanelMouseController implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		int n = e.getClickCount();
		if(n == 2){
			FilePanel panel = (FilePanel)e.getSource();
			Desktop desk = Desktop.getDesktop();
			try {
				desk.open(panel.getFile());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
