package prd.fms.view;

import java.io.File;

import javax.swing.table.AbstractTableModel;

/**
 * <p>File list table model used to sort.</p>
 * 
 * @author zhoubo
 * 
 */
public class SortedFileListTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private FileListTableModel model;
	
	public SortedFileListTableModel(FileListTableModel model) {
		this.model = model;
	}
	
	@Override
	public int getRowCount() {
		return model.getRowCount();
	}

	@Override
	public int getColumnCount() {
		return model.getColumnCount();
	}

	@Override
	public String getColumnName(int column) {
		return model.getColumnName(column);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object obj = null;
		
		try{
			if(columnIndex == 0) {
				obj = model.getFiles().get(rowIndex).getName();
			} else if(columnIndex == 1) {
				obj = model.getValueAt(rowIndex, columnIndex);
			} else {
				File tmpFile = model.getFiles().get(rowIndex);
				if(tmpFile.isDirectory()) {
					obj = 0l;
				} else {
					obj = model.getFiles().get(rowIndex).length();
				}
			}
		} catch (ArrayIndexOutOfBoundsException  e) {
			return obj;
		}
		return obj;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex < 2) {
			return String.class;
		} else {
			return Long.class;
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		model.setValueAt(aValue, rowIndex, columnIndex);
    }
}
