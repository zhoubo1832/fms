package prd.fms.executor;

import java.awt.event.KeyEvent;

import prd.fms.controller.BaseKeyListener;

public class FilePanelKeyExecutor extends BaseKeyListener{

	public static boolean controlKeyPressed = false;
	public static boolean shiftKeyPressed = false;

	@Override
	protected void executePressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
			controlKeyPressed = true;
		} else if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			shiftKeyPressed = true;
		}		
	}

	@Override
	protected void executeReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
			controlKeyPressed = false;
		} else if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			shiftKeyPressed = false;
		}		
	}

}
