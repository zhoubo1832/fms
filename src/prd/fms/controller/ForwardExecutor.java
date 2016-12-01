package prd.fms.controller;

import prd.fms.common.CommandManager;
import prd.fms.view.ToolbarPanel;

public class ForwardExecutor extends BaseController{

	@Override
	protected void execute() {
		ToolbarPanel.instance.setForwardButtonClicked(true);
		CommandManager.forward();
		if(!CommandManager.isCanForward()) {
			ToolbarPanel.instance.setForwardButtonEnable(false);
		}
		ToolbarPanel.instance.setBackButtonEnable(true);		
	}

}
