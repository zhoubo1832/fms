package prd.fms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import prd.fms.model.FileNodeModel;
import prd.fms.model.TreeNodeModel;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.RenameDialog;
import prd.fms.view.ViewConstants;

public class RenameDialogController implements ActionListener, DocumentListener{
	private RenameDialog dialog;
	
	public RenameDialogController(RenameDialog dialog) {
		this.dialog = dialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn.getText().equals(ViewConstants.COMMON_BUTTON_OK) ) {
			String path = InfoBarPanel.selectedPath;
			String newName = dialog.getNewNameTf().getText();
			
			File oldFile = new File(path);
			String tmpStr = path.substring(0, path.lastIndexOf("\\") + 1);
			File newFile = new File(tmpStr + newName);
			oldFile.renameTo(newFile);
			dialog.dispose();
			
			FileNodeModel.refresh();
			TreeNodeModel.refresh();
			
		} else if(btn.getText().equals(ViewConstants.COMMON_BUTTON_CANCEL) ) {
			dialog.dispose();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		setOkBtnEnable();
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		setOkBtnEnable();
		
	}

	private void setOkBtnEnable() {
		String newText = dialog.getNewNameTf().getText();
		if(newText == null || newText.length() == 0 || dialog.getOldText().equals(newText)) {
			dialog.getOkBtn().setEnabled(false);
		} else {
			dialog.getOkBtn().setEnabled(true);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
