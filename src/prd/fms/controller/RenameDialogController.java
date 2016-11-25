package prd.fms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import prd.fms.model.FileNodeModel;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.RenameDialog;
import prd.fms.view.ViewConstants;

public class RenameDialogController implements ActionListener{
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
			
		} else if(btn.getText().equals(ViewConstants.COMMON_BUTTON_CANCEL) ) {
			dialog.dispose();
		}
	}

}
