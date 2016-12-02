package prd.fms.controller;

import javax.swing.event.DocumentEvent;

import prd.fms.view.NewfolderDialog;

public class NewfolderDialogDocumentExecutor extends BaseDocumentListener{

	private NewfolderDialog dialog;
	
	public NewfolderDialogDocumentExecutor(NewfolderDialog dialog) {
		this.dialog = dialog;
	}
	
	@Override
	protected void executeInsert(DocumentEvent e) {
		setOkBtnEnable();			
	}

	@Override
	protected void executeRemove(DocumentEvent e) {
		setOkBtnEnable();			
	}

	@Override
	protected void executeChange(DocumentEvent e) {
		
	}

	private void setOkBtnEnable() {
		dialog.getMsgLabel().setText("");
		String newText = dialog.getNewNameTf().getText();
		if(newText == null || newText.length() == 0) {
			dialog.getOkBtn().setEnabled(false);
		} else {
			dialog.getOkBtn().setEnabled(true);
		}
	}
}
