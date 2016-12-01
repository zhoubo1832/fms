package prd.fms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import prd.fms.model.FileNodeModel;

public abstract class BaseController implements ActionListener, ItemListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		execute();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		execute();
	}
	
	protected abstract void execute();
}
