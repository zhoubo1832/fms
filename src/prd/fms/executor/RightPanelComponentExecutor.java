package prd.fms.executor;

import java.awt.event.ComponentEvent;

import prd.fms.controller.BaseComponentListener;
import prd.fms.model.FileNodeModel;

public class RightPanelComponentExecutor extends BaseComponentListener{
	
	@Override
	protected void executeResized(ComponentEvent e){
		FileNodeModel.refresh();
	}
	
}
