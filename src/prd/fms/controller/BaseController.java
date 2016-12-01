package prd.fms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		execute();
	}

	protected abstract void execute();
}
