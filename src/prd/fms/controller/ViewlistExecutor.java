package prd.fms.controller;

import prd.fms.model.FileNodeModel;

public class ViewlistExecutor extends BaseController{

	@Override
	protected void execute() {
		FileNodeModel.refresh();		
	}

}
