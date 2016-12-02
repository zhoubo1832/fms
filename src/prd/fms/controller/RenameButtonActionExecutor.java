package prd.fms.controller;

import java.awt.event.ActionEvent;

import prd.fms.view.MainFrame;
import prd.fms.view.RenameDialog;

public class RenameButtonActionExecutor extends BaseActionListener{

	@Override
	protected void execute(ActionEvent e) {
		new RenameDialog(MainFrame.instance, true);		
	}

}
