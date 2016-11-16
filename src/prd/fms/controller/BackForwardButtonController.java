package prd.fms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import prd.fms.common.CommandManager;
import prd.fms.view.ViewConstants;

public class BackForwardButtonController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn.getText().equals(ViewConstants.TOOLBAR_BACK_TEXT) ) {
			CommandManager.back();
		} else {
			
		}
	}

}
