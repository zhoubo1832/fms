package prd.fms.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import prd.fms.common.SelectedFileList;
import prd.fms.model.FileSystemModel;
import prd.fms.util.CommonUtils;
import prd.fms.view.MainFrame;
import prd.fms.view.MainTree;
import prd.fms.view.PasteAlertDialog;
import prd.fms.view.ViewConstants;

public class PasteButtonActionExecutor extends BaseActionListener{

	@Override
	protected void execute(ActionEvent e) {
		String desPath = CommonUtils.getPath(MainTree.instance.getSelectionPath());
		ArrayList<String> list = SelectedFileList.getInstance().getPathList();
		String[] srcPathArray = new String[list.size()];
		list.toArray(srcPathArray);
//		FileSystemModel.copyFiles(srcPathArray, desPath);
		
		for(String file : srcPathArray) {
			if(new File(file).isDirectory()) {
				File fileDest = new File(FileSystemModel.getDestPath(file, desPath));
				if(fileDest.exists()) {
					if(fileDest.isDirectory()) {
						PasteAlertDialog.setMsg(ViewConstants.PASTE_ALERT_MESSAGE_01);
						PasteAlertDialog.setFileName(file);
						new PasteAlertDialog(MainFrame.instance, true);
					} else {
						PasteAlertDialog.setMsg(ViewConstants.PASTE_ALERT_MESSAGE_02);
						PasteAlertDialog.setFileName(file);
						new PasteAlertDialog(MainFrame.instance, true);
					}
				} else {
					FileSystemModel.copyDir(file, desPath);
				}
			} else {
				File fileDest = new File(FileSystemModel.getDestPath(file, desPath));
				if(fileDest.exists()) {
					return;
				}
				FileSystemModel.copyFile(file, desPath);
			}
		}
				
	}

}
