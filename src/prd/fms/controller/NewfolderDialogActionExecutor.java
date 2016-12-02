package prd.fms.controller;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import prd.fms.model.FileNodeModel;
import prd.fms.model.FileSystemModel;
import prd.fms.model.TreeNodeModel;
import prd.fms.util.CommonUtils;
import prd.fms.view.MainTree;
import prd.fms.view.NewfolderDialog;
import prd.fms.view.ViewConstants;

public class NewfolderDialogActionExecutor extends BaseActionListener{
	
	private NewfolderDialog dialog;
	
	public NewfolderDialogActionExecutor(NewfolderDialog dialog) {
		this.dialog = dialog;
	}
	
	@Override
	protected void execute(ActionEvent e) {
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

}
