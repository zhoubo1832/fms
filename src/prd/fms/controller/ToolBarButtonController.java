package prd.fms.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import prd.fms.common.CommandManager;
import prd.fms.common.SelectedFileList;
import prd.fms.model.FileSystemModel;
import prd.fms.util.CommonUtils;
import prd.fms.view.AlertDialog;
import prd.fms.view.FileListPanel;
import prd.fms.view.FileListTableModel;
import prd.fms.view.FilePanel;
import prd.fms.view.MainFrame;
import prd.fms.view.MainTree;
import prd.fms.view.NewfolderDialog;
import prd.fms.view.RenameDialog;
import prd.fms.view.RightPanel;
import prd.fms.view.ToolbarPanel;
import prd.fms.view.ViewConstants;

/**
 * <p>Back and forward button's action listener.</p>
 * 
 * @author zhoubo
 * 
 */
public class ToolBarButtonController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn.getText().equals(ViewConstants.TOOLBAR_BACK_TEXT) ) {
			ToolbarPanel.instance.setBackButtonClicked(true);
			CommandManager.back();
			if(!CommandManager.isCanBack()) {
				ToolbarPanel.instance.setBackButtonEnable(false);
			}
			ToolbarPanel.instance.setForwardButtonEnable(true);
		} else if(btn.getText().equals(ViewConstants.TOOLBAR_FORWARD_TEXT)) {
			ToolbarPanel.instance.setForwardButtonClicked(true);
			CommandManager.forward();
			if(!CommandManager.isCanForward()) {
				ToolbarPanel.instance.setForwardButtonEnable(false);
			}
			ToolbarPanel.instance.setBackButtonEnable(true);
		} else if(btn.getText().equals(ViewConstants.TOOLBAR_RENAME_TEXT)) {
			new RenameDialog(MainFrame.instance, true);
		} else if(btn.getText().equals(ViewConstants.TOOLBAR_NEWFOLDER_TEXT)) {
			new NewfolderDialog(MainFrame.instance, true);
		} else if(btn.getText().equals(ViewConstants.TOOLBAR_COPY_TEXT)) {
			SelectedFileList.getInstance().clear();
			
			int selectedIndex = ToolbarPanel.instance.getViewList().getSelectedIndex();
			if(selectedIndex == 0) {
				JPanel panel = RightPanel.instance.getParentFilePanel();
				
				for( Component p : panel.getComponents()) {
					FilePanel fPanel = (FilePanel) p;
					if(fPanel.getBorder() != null) {
						SelectedFileList.getInstance().addPath(fPanel.getFile().getPath());
					}
				}
			} else {
				JTable fileTable = FileListPanel.instance.getFileTable();
				int[] selectedRows = fileTable.getSelectedRows();
				
				for(int rowNum : selectedRows) {
					rowNum = fileTable.convertRowIndexToModel(rowNum);
					FileListTableModel model = (FileListTableModel)fileTable.getModel();
					String path = (model.getFiles()[rowNum]).getPath();
					SelectedFileList.getInstance().addPath(path);
				}
			}
			ToolbarPanel.instance.setPasteButtonEnabled(true);
			
		} else if(btn.getText().equals(ViewConstants.TOOLBAR_PASTE_TEXT)) {
			String desPath = CommonUtils.getPath(MainTree.instance.getSelectionPath());
			ArrayList<String> list = SelectedFileList.getInstance().getPathList();
			String[] srcPathArray = new String[list.size()];
			list.toArray(srcPathArray);
//			FileSystemModel.copyFiles(srcPathArray, desPath);
			
			for(String file : srcPathArray) {
				if(new File(file).isDirectory()) {
					File fileDest = new File(FileSystemModel.getDestPath(file, desPath));
					if(fileDest.exists()) {
						if(fileDest.isDirectory()) {
							new AlertDialog(MainFrame.instance, true, ViewConstants.COMMON_ALERT_MESSAGE_01, file);
						} else {
							new AlertDialog(MainFrame.instance, true, ViewConstants.COMMON_ALERT_MESSAGE_02, file);
						}
						return;
					}
					
					FileSystemModel.copyDir(file, desPath);
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

}
