package prd.fms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import prd.fms.model.FileNodeModel;

public abstract class BaseActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		execute(e);
	}
	
	protected abstract void execute(ActionEvent e);
}
