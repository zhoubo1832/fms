package prd.fms.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import prd.fms.model.FileNodeModel;
import prd.fms.view.FilePanel;
import prd.fms.view.InfoBarPanel;

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
