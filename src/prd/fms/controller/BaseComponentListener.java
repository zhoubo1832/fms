package prd.fms.controller;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public abstract class BaseComponentListener implements ComponentListener{

	@Override
	public void componentResized(ComponentEvent e) {
		executeResized(e);
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		executeMoved(e);
	}

	@Override
	public void componentShown(ComponentEvent e) {
		executeShown(e);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		executeHidden(e);
	}

	protected void executeResized(ComponentEvent e){}
	
	protected void executeMoved(ComponentEvent e){}
	
	protected void executeShown(ComponentEvent e){}
	
	protected void executeHidden(ComponentEvent e){}
	
}
