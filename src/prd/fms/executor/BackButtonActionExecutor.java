package prd.fms.executor;

import java.awt.event.ActionEvent;

import prd.fms.common.CommandManager;
import prd.fms.controller.BaseActionListener;
import prd.fms.view.ToolbarPanel;

public class BackButtonActionExecutor extends BaseActionListener{

	@Override
	protected void execute(ActionEvent e) {
		ToolbarPanel.instance.setBackButtonClicked(true);
		CommandManager.back();
		if(!CommandManager.isCanBack()) {
			ToolbarPanel.instance.setBackButtonEnable(false);
		}
		ToolbarPanel.instance.setForwardButtonEnable(true);		
	}

}
