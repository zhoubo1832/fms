package prd.fms.executor;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;

import prd.fms.controller.BaseActionListener;
import prd.fms.model.FileNodeModel;
import prd.fms.model.FileSystemModel;
import prd.fms.model.TreeNodeModel;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.RenameDialog;
import prd.fms.view.ViewConstants;

public class RenameDialogActionExecutor extends BaseActionListener{

	private RenameDialog dialog;
	
	public RenameDialogActionExecutor(RenameDialog dialog) {
		this.dialog = dialog;
	}
	
	@Override
	protected void execute(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn.getText().equals(ViewConstants.COMMON_BUTTON_OK) ) {
			String path = InfoBarPanel.selectedPath;
			String newName = dialog.getNewNameTf().getText();
			
			boolean isDir = new File(path).isDirectory();
			// rename file/folder
			int retCode = FileSystemModel.rename(path, newName);
			if(retCode == 1) {
				dialog.getMsgLabel().setText(ViewConstants.RENAME_EXIST_MSG);
				return;
			}
			
			dialog.dispose();
			
			// refresh right panel
			FileNodeModel.refresh();
			
			// refresh tree node
			if(isDir) {
				TreeNodeModel.refresh();
			}
			
		} else if(btn.getText().equals(ViewConstants.COMMON_BUTTON_CANCEL) ) {
			dialog.dispose();
		}		
	}

}
