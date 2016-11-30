package prd.fms.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import prd.fms.common.CommandManager;
import prd.fms.common.SelectedFileList;
import prd.fms.view.FileListPanel;
import prd.fms.view.FileListTableModel;
import prd.fms.view.FilePanel;
import prd.fms.view.MainFrame;
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
			int selectedIndex = ToolbarPanel.instance.getViewList().getSelectedIndex();
			if(selectedIndex == 0) {
//				ArrayList<FilePanel> list = SelectedFileList.getInstance().getPanelList();
				JPanel panel = RightPanel.instance.getParentFilePanel();
				
				for( Component p : panel.getComponents()) {
					FilePanel fPanel = (FilePanel) p;
					if(fPanel.getBorder() != null) {
						System.out.println(fPanel.getFile().getPath());
					}
//					System.out.println(panel.getFile().getPath());
				}
			} else {
				SelectedFileList.getInstance().clear();
				JTable fileTable = FileListPanel.instance.getFileTable();
				int[] selectedRows = fileTable.getSelectedRows();
				
				for(int rowNum : selectedRows) {
					rowNum = fileTable.convertRowIndexToModel(rowNum);
					FileListTableModel model = (FileListTableModel)fileTable.getModel();
					String path = (model.getFiles()[rowNum]).getPath();
					SelectedFileList.getInstance().addPath(path);
					System.out.println("list:" + path);
				}
			}
			
		}
	}

}
