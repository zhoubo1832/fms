package prd.fms.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class BaseMouseListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		executeClicked(e);		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		executePressed(e);		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		executeReleased(e);		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		executeEntered(e);		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		executeExited(e);		
	}

	protected void executeClicked(MouseEvent e){}
	protected void executePressed(MouseEvent e){}
	protected void executeReleased(MouseEvent e){}
	protected void executeEntered(MouseEvent e){}
	protected void executeExited(MouseEvent e){}
}
