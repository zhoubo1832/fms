package prd.fms.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import prd.fms.model.FileNodeModel;

public class ViewListItemController implements ItemListener{

	@Override
	public void itemStateChanged(ItemEvent e) {
		FileNodeModel.refresh();
	}
}
