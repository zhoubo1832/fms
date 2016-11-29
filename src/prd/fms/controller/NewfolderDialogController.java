package prd.fms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import prd.fms.model.FileNodeModel;
import prd.fms.model.FileSystemModel;
import prd.fms.model.TreeNodeModel;
import prd.fms.util.CommonUtils;
import prd.fms.view.MainTree;
import prd.fms.view.NewfolderDialog;
import prd.fms.view.ViewConstants;

public class NewfolderDialogController implements ActionListener, DocumentListener{

	private NewfolderDialog dialog;
	
	public NewfolderDialogController(NewfolderDialog dialog) {
		this.dialog = dialog;
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		setOkBtnEnable();		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		setOkBtnEnable();		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn.getText().equals(ViewConstants.COMMON_BUTTON_OK) ) {
			String path = CommonUtils.getPath(MainTree.instance.getSelectionPath());
			String newName = dialog.getNewNameTf().getText();
			
			// new folder
			int retCode = FileSystemModel.newFolder(path, newName);
			if(retCode == 1) {
				dialog.getMsgLabel().setText(ViewConstants.NEWFOLDER_FOLDER_EXIST_MSG);
				return;
			}
		
			dialog.dispose();
			
			// refresh right panel
			FileNodeModel.refresh();
			
			// refresh tree node
			TreeNodeModel.refresh();
			
		} else if(btn.getText().equals(ViewConstants.COMMON_BUTTON_CANCEL) ) {
			dialog.dispose();
		}
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
