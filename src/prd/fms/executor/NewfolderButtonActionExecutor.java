package prd.fms.executor;

import java.awt.event.ActionEvent;

import prd.fms.controller.BaseActionListener;
import prd.fms.view.MainFrame;
import prd.fms.view.NewfolderDialog;

public class NewfolderButtonActionExecutor extends BaseActionListener{

	@Override
	protected void execute(ActionEvent e) {
		new NewfolderDialog(MainFrame.instance, true);		
	}

}
