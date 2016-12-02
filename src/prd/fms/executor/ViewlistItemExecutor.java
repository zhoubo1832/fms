package prd.fms.executor;

import prd.fms.controller.BaseItemListener;
import prd.fms.model.FileNodeModel;

public class ViewlistItemExecutor extends BaseItemListener{

	@Override
	protected void execute() {
		FileNodeModel.refresh();		
	}

}
