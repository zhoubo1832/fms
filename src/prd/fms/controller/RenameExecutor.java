package prd.fms.controller;

import prd.fms.view.MainFrame;
import prd.fms.view.RenameDialog;

public class RenameExecutor extends BaseController{

	@Override
	protected void execute() {
		new RenameDialog(MainFrame.instance, true);		
	}

}
