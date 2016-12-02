package prd.fms.controller;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;

public abstract class BaseTreeWillExpandListener implements TreeWillExpandListener{

	@Override
	public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
		executeExpand(event);		
	}

	@Override
	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
		executeCollapse(event);		
	}
	
	protected void executeExpand(TreeExpansionEvent event){}
	protected void executeCollapse(TreeExpansionEvent event){}

}
