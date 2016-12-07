package prd.fms.controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class BaseFocusListener implements FocusListener{

	@Override
	public void focusGained(FocusEvent e) {
		executeGained(e);
	}

	@Override
	public void focusLost(FocusEvent e) {
		executeLost(e);		
	}

	protected void executeGained(FocusEvent e) {}

	protected void executeLost(FocusEvent e) {}
}
