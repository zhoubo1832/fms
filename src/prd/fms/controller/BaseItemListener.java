package prd.fms.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public abstract class BaseItemListener implements ItemListener{
	@Override
	public void itemStateChanged(ItemEvent e) {
		execute();
	}
	
	protected void execute(){}
}
