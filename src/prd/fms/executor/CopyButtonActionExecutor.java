package prd.fms.executor;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JTable;

import prd.fms.common.SelectedFileList;
import prd.fms.controller.BaseActionListener;
import prd.fms.view.FileListPanel;
import prd.fms.view.FileListTableModel;
import prd.fms.view.FilePanel;
import prd.fms.view.RightPanel;
import prd.fms.view.ToolbarPanel;

public class CopyButtonActionExecutor extends BaseActionListener{

	@Override
	protected void execute(ActionEvent e) {
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
				String path = (model.getFiles().get(rowNum)).getPath();
				SelectedFileList.getInstance().addPath(path);
			}
		}
		ToolbarPanel.instance.setPasteButtonEnabled(true);		
	}

}
