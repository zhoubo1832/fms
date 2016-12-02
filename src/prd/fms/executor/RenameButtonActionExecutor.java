package prd.fms.executor;

import java.awt.event.ActionEvent;

import prd.fms.controller.BaseActionListener;
import prd.fms.view.MainFrame;
import prd.fms.view.RenameDialog;

public class RenameButtonActionExecutor extends BaseActionListener{

	@Override
	protected void execute(ActionEvent e) {
		new RenameDialog(MainFrame.instance, true);		
	}

}
