package prd.fms.executor;

import javax.swing.event.DocumentEvent;

import prd.fms.controller.BaseDocumentListener;
import prd.fms.view.RenameDialog;

public class RenameDialogDocumentExecutor extends BaseDocumentListener{

	private RenameDialog dialog;
	
	public RenameDialogDocumentExecutor(RenameDialog dialog) {
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

	private void setOkBtnEnable() {
		dialog.getMsgLabel().setText("");
		String newText = dialog.getNewNameTf().getText();
		if(newText == null || newText.length() == 0 || dialog.getOldText().equals(newText)) {
			dialog.getOkBtn().setEnabled(false);
		} else {
			dialog.getOkBtn().setEnabled(true);
		}
	}
}
