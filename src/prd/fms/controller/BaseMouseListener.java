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

	protected abstract void executeClicked(MouseEvent e);
	protected abstract void executePressed(MouseEvent e);
	protected abstract void executeReleased(MouseEvent e);
	protected abstract void executeEntered(MouseEvent e);
	protected abstract void executeExited(MouseEvent e);
}
