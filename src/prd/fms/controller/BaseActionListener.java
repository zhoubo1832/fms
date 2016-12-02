package prd.fms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		execute(e);
	}
	
	protected void execute(ActionEvent e){}
}
