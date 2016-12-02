package prd.fms.controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class BaseDocumentListener implements DocumentListener{

	@Override
	public void insertUpdate(DocumentEvent e) {
		executeInsert(e);		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		executeRemove(e);		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		executeChange(e);		
	}
	
	protected void executeInsert(DocumentEvent e){}
	
	protected void executeRemove(DocumentEvent e){}
	
	protected void executeChange(DocumentEvent e){}

}
