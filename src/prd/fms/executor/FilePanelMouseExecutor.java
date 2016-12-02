package prd.fms.executor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import prd.fms.controller.BaseMouseListener;
import prd.fms.model.FileNodeModel;
import prd.fms.view.FilePanel;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.ToolbarPanel;

public class FilePanelMouseExecutor extends BaseMouseListener{

	@Override
	protected void executeClicked(MouseEvent e) {
		int n = e.getClickCount();
		// double click
		if(n == 2){
			FilePanel panel = (FilePanel)e.getSource();
			File file = panel.getFile();
			FileNodeModel.openFileNode(file);
			
			ToolbarPanel.instance.setRenameButtonEnabled(false);
			ToolbarPanel.instance.setCopyButtonEnabled(false);
			
		} else {
		// one click	
			FilePanel panel = (FilePanel)e.getSource();
			boolean flgControl = FilePanelKeyExecutor.controlKeyPressed;
			boolean flgShift = FilePanelKeyExecutor.shiftKeyPressed;
			if(!flgControl && !flgShift) {
				Container container = panel.getParent();
				for( Component p : container.getComponents()) {
					if(p != panel) {
						((JComponent) p).setBorder(null);
						p.setBackground(null);
					} else {
						panel.requestFocus();
						panel.setBackground(new Color(219,243,146));
						panel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
					}
				}

			} else if(flgShift) {
				Container container = panel.getParent();
				int i = 0;
				for( ; i < container.getComponentCount(); i++) {
					Component p = container.getComponent(i);
					if(p == panel) {
						break;
					}
				}
				
				int j = 0;
				for( ; j < container.getComponentCount(); j++) {
					Component p = container.getComponent(j);
					if(((JComponent) p).getBorder() != null && j != i) {
						break;
					}
				}
				
				if(j >= container.getComponentCount()) {
					j = i;
				}
				
				System.out.println("i=" + i);
				System.out.println("j=" + j);
				
				int begin=i;
				int end=j;
				if(i > j) {
					begin = j;
					end = i;
				}
				
				while(begin <= end) {
					Component p = container.getComponent(begin);
					p.requestFocus();
					p.setBackground(new Color(219,243,146));
					((JComponent) p).setBorder(BorderFactory.createRaisedSoftBevelBorder());
					begin++;
				}
			} else {
				panel.requestFocus();
				panel.setBackground(new Color(219,243,146));
				panel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
			}
//			Container container = panel.getParent();
//			container.get
			// request focus for file panel, and focusGained method will be called automatically
//			panel.requestFocus();
//			panel.setBackground(new Color(219,243,146));
//			panel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
			
			ToolbarPanel.instance.setRenameButtonEnabled(true);
			ToolbarPanel.instance.setCopyButtonEnabled(true);
			
			// display file detailed information in information panel
			InfoBarPanel.instance.setFileInfoLabel(panel.getFile().getPath());
		}

	
		
	}

	@Override
	protected void executeEntered(MouseEvent e) {

		JPanel panel = (JPanel)e.getSource();
		if(panel.getBorder() == null) {
			panel.setBackground(new Color(232,255,232));
			panel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		}
	
		
	}

	@Override
	protected void executeExited(MouseEvent e) {
		JPanel panel = (JPanel)e.getSource();
		if(panel.getBackground().equals(new Color(232,255,232))) {
			panel.setBorder(null);
			panel.setBackground(null);
		}
		
	}

}
