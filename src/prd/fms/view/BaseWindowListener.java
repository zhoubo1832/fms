package prd.fms.view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public abstract class BaseWindowListener implements WindowListener{

	@Override
	public void windowOpened(WindowEvent e) {
		executeOpened(e);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		executeClosing(e);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		executeClosed(e);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		executeIconified(e);
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		executeDeiconified(e);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		executeActivated(e);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		executeDeactivated(e);
	}

	protected void executeOpened(WindowEvent e) {}
	protected void executeClosing(WindowEvent e) {}
	protected void executeClosed(WindowEvent e) {}
	protected void executeIconified(WindowEvent e) {}
	protected void executeDeiconified(WindowEvent e) {}
	protected void executeActivated(WindowEvent e) {}
	protected void executeDeactivated(WindowEvent e) {}
	
}
