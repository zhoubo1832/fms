package prd.fms.controller;

import prd.fms.model.FileNodeModel;

public class ViewlistItemExecutor extends BaseItemListener{

	@Override
	protected void execute() {
		FileNodeModel.refresh();		
	}

}
