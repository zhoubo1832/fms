package prd.fms.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class BaseKeyListener implements KeyListener{
	@Override
	public void keyTyped(KeyEvent e) {
		executeTyped(e);
	}

	@Override
    public void keyPressed(KeyEvent e) {
		executePressed(e);
	}

	@Override
    public void keyReleased(KeyEvent e) {
		executeReleased(e);
	}
	
	protected void executeTyped(KeyEvent e){}
	
	protected void executePressed(KeyEvent e){}
	
	protected void executeReleased(KeyEvent e){}
}
