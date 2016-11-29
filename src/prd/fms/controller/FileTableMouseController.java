package prd.fms.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JTable;
import prd.fms.model.FileNodeModel;
import prd.fms.view.FileListTableModel;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.ToolbarPanel;

/**
 * <p>File table's mouse listener.</p>
 * 
 * @author zhoubo
 * 
 */
public class FileTableMouseController  implements MouseListener{

	private JTable fileTable;
	
	public FileTableMouseController(JTable fileTable) {
		this.fileTable = fileTable;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int n = e.getClickCount();
		// double click
		if( n == 2) {
			int rowNum = fileTable.getSelectedRow();
			rowNum = fileTable.convertRowIndexToModel(rowNum);
			FileListTableModel model = (FileListTableModel)fileTable.getModel();
			File file = model.getFiles()[rowNum];
			FileNodeModel.openFileNode(file);
			ToolbarPanel.instance.setRenameButtonEnabled(false);
		} else {
		// one click	
			int rowNum = fileTable.getSelectedRow();
			rowNum = fileTable.convertRowIndexToModel(rowNum);
			FileListTableModel model = (FileListTableModel)fileTable.getModel();
			String path = (model.getFiles()[rowNum]).getPath();
			InfoBarPanel.instance.setFileInfoLabel(path);
			ToolbarPanel.instance.setRenameButtonEnabled(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
