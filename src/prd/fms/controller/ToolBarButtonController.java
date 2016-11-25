package prd.fms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import prd.fms.common.CommandManager;
import prd.fms.view.MainFrame;
import prd.fms.view.RenameDialog;
import prd.fms.view.ToolbarPanel;
import prd.fms.view.ViewConstants;

/**
 * <p>Back and forward button's action listener.</p>
 * 
 * @author zhoubo
 * 
 */
public class ToolBarButtonController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn.getText().equals(ViewConstants.TOOLBAR_BACK_TEXT) ) {
			ToolbarPanel.instance.setBackButtonClicked(true);
			CommandManager.back();
			if(!CommandManager.isCanBack()) {
				ToolbarPanel.instance.setBackButtonEnable(false);
			}
			ToolbarPanel.instance.setForwardButtonEnable(true);
		} else if(btn.getText().equals(ViewConstants.TOOLBAR_FORWARD_TEXT)) {
			ToolbarPanel.instance.setForwardButtonClicked(true);
			CommandManager.forward();
			if(!CommandManager.isCanForward()) {
				ToolbarPanel.instance.setForwardButtonEnable(false);
			}
			ToolbarPanel.instance.setBackButtonEnable(true);
		} else if(btn.getText().equals(ViewConstants.TOOLBAR_RENAME_TEXT)) {
			RenameDialog frm = new RenameDialog(MainFrame.instance, true);
			
		}
	}

}
