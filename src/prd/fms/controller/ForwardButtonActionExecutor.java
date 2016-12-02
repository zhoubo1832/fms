package prd.fms.controller;

import java.awt.event.ActionEvent;

import prd.fms.common.CommandManager;
import prd.fms.view.ToolbarPanel;

public class ForwardButtonActionExecutor extends BaseActionListener{

	@Override
	protected void execute(ActionEvent e) {
		ToolbarPanel.instance.setForwardButtonClicked(true);
		CommandManager.forward();
		if(!CommandManager.isCanForward()) {
			ToolbarPanel.instance.setForwardButtonEnable(false);
		}
		ToolbarPanel.instance.setBackButtonEnable(true);		
	}

}
