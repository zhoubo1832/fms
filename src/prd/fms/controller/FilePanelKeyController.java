package prd.fms.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FilePanelKeyController extends KeyAdapter{
	public static boolean controlKeyPressed = false;
	public static boolean shiftKeyPressed = false;
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
			controlKeyPressed = true;
		} else if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			shiftKeyPressed = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
			controlKeyPressed = false;
		} else if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			shiftKeyPressed = false;
		}
	}
	
}
