package prd.fms.controller;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public abstract class BaseTreeSelectionListener implements TreeSelectionListener{

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		execute(e);
	}
	
	protected abstract void execute(TreeSelectionEvent e);

}
