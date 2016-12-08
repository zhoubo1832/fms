package prd.fms.executor;

import java.awt.event.WindowEvent;

import prd.fms.view.BaseWindowListener;

public class ProgressBarFrameWindowExecutor extends BaseWindowListener{

	private Thread thread;
	
	public ProgressBarFrameWindowExecutor(Thread thread) {
		this.thread = thread;
	}
	@Override
	protected void executeClosed(WindowEvent e) {
		thread.interrupt();
	}
}
