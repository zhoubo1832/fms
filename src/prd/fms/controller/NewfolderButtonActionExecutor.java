package prd.fms.controller;

import java.awt.event.ActionEvent;

import prd.fms.view.MainFrame;
import prd.fms.view.NewfolderDialog;

public class NewfolderButtonActionExecutor extends BaseActionListener{

	@Override
	protected void execute(ActionEvent e) {
		new NewfolderDialog(MainFrame.instance, true);		
	}

}
