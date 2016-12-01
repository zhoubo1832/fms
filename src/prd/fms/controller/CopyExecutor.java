package prd.fms.controller;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;

import prd.fms.common.SelectedFileList;
import prd.fms.view.FileListPanel;
import prd.fms.view.FileListTableModel;
import prd.fms.view.FilePanel;
import prd.fms.view.RightPanel;
import prd.fms.view.ToolbarPanel;

public class CopyExecutor extends BaseController{

	@Override
	protected void execute() {
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
	}

}
