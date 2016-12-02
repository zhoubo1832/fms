package prd.fms.executor;

import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JTable;

import prd.fms.controller.BaseMouseListener;
import prd.fms.model.FileNodeModel;
import prd.fms.view.FileListTableModel;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.ToolbarPanel;

public class FileTableMouseExecutor extends BaseMouseListener{

	private JTable fileTable;
	
	public FileTableMouseExecutor(JTable fileTable) {
		this.fileTable = fileTable;
	}
	
	@Override
	protected void executeClicked(MouseEvent e) {
		int n = e.getClickCount();
		// double click
		if( n == 2) {
			int rowNum = fileTable.getSelectedRow();
			rowNum = fileTable.convertRowIndexToModel(rowNum);
			FileListTableModel model = (FileListTableModel)fileTable.getModel();
			File file = model.getFiles()[rowNum];
			FileNodeModel.openFileNode(file);
			ToolbarPanel.instance.setRenameButtonEnabled(false);
			ToolbarPanel.instance.setCopyButtonEnabled(false);
		} else {
		// one click	
			int rowNum = fileTable.getSelectedRow();
			rowNum = fileTable.convertRowIndexToModel(rowNum);
			FileListTableModel model = (FileListTableModel)fileTable.getModel();
			String path = (model.getFiles()[rowNum]).getPath();
			InfoBarPanel.instance.setFileInfoLabel(path);
			ToolbarPanel.instance.setRenameButtonEnabled(true);
			ToolbarPanel.instance.setCopyButtonEnabled(true);
		}		
	}

	@Override
	protected void executePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void executeReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void executeEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void executeExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}