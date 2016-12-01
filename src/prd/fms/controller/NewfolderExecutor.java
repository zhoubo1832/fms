package prd.fms.controller;

import prd.fms.view.MainFrame;
import prd.fms.view.NewfolderDialog;

public class NewfolderExecutor extends BaseController{

	@Override
	protected void execute() {
		new NewfolderDialog(MainFrame.instance, true);		
	}

}
