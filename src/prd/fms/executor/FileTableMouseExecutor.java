package prd.fms.executor;

import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.JTable;

import prd.fms.controller.BaseMouseListener;
import prd.fms.model.FileNodeModel;
import prd.fms.model.TagModel;
import prd.fms.view.FileListTableModel;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.MainFrame;
import prd.fms.view.TagDialog;
import prd.fms.view.ToolbarPanel;

public class FileTableMouseExecutor extends BaseMouseListener{

	private JTable fileTable;
	
	public FileTableMouseExecutor(JTable fileTable) {
		this.fileTable = fileTable;
	}
	
	@Override
	protected void executeClicked(MouseEvent e) {
		if(e.getButton() == 1) {
			int n = e.getClickCount();
			// double click
			if( n == 2) {
				int rowNum = fileTable.getSelectedRow();
				if(rowNum == -1) {
					return;
				}
				rowNum = fileTable.convertRowIndexToModel(rowNum);
				FileListTableModel model = (FileListTableModel)fileTable.getModel();
				File file = model.getFiles().get(rowNum);
				FileNodeModel.openFileNode(file);
				ToolbarPanel.instance.setRenameButtonEnabled(false);
				ToolbarPanel.instance.setCopyButtonEnabled(false);
			} else {
			// one click	
				int rowNum = fileTable.getSelectedRow();
				if(rowNum == -1) {
					return;
				}
				rowNum = fileTable.convertRowIndexToModel(rowNum);
				FileListTableModel model = (FileListTableModel)fileTable.getModel();
				String path = (model.getFiles().get(rowNum)).getPath();
				InfoBarPanel.instance.setFileInfoLabel(path);
				ToolbarPanel.instance.setRenameButtonEnabled(true);
				ToolbarPanel.instance.setCopyButtonEnabled(true);
			}
		} else if(e.getButton() == 3) {
			int rowNum = fileTable.rowAtPoint(e.getPoint());
			if(rowNum == -1) {
				return;
			}
			fileTable.setRowSelectionInterval(rowNum, rowNum);
			rowNum = fileTable.convertRowIndexToModel(rowNum);
			
			FileListTableModel model = (FileListTableModel)fileTable.getModel();
			String filePath = (model.getFiles().get(rowNum)).getPath();
			List<Object> list = TagModel.getFileTags(filePath);
			new TagDialog(MainFrame.instance, "Tags", true, filePath, list);
		}
	}

}