package prd.fms.controller;

import prd.fms.common.CommandManager;
import prd.fms.view.ToolbarPanel;

public class BackExecutor extends BaseController{

	@Override
	protected void execute() {
		ToolbarPanel.instance.setBackButtonClicked(true);
		CommandManager.back();
		if(!CommandManager.isCanBack()) {
			ToolbarPanel.instance.setBackButtonEnable(false);
		}
		ToolbarPanel.instance.setForwardButtonEnable(true);		
	}

}
