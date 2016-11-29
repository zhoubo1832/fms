package prd.fms.view;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;

import prd.fms.bean.FileListItem;
import prd.fms.util.CommonUtils;

/**
 * <p>File list table model.</p>
 * 
 * @author zhoubo
 * 
 */
public class FileListTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;

	private Vector<FileListItem> vecData;
	
	private final String[] columNames = {"Name","Update date","Size"};
	
	private File[] files;


	public FileListTableModel() {
		
	}
	
	public FileListTableModel(File[] files) {
		this.files = files;
		vecData = new Vector<FileListItem>();
		for(File file : files) {
			FileListItem item = new FileListItem();
			item.setFileLabel(this.getFileLabel(file));
			item.setFileUpdateDate(this.getFileUpdateDate(file));
			item.setFileSize(this.getFileSize(file));
			
			this.vecData.add(item);
		}
	}
	
	
	@Override
	public int getRowCount() {
		return vecData.size();
	}

	@Override
	public int getColumnCount() {
		return columNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return columNames[column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object obj = null;
		try{
			if(columnIndex == 0) {
				obj = vecData.get(rowIndex).getFileLabel();
			} else if(columnIndex == 1) {
				obj = vecData.get(rowIndex).getFileUpdateDate();
			} else {
				obj = vecData.get(rowIndex).getFileSize();
			}
		} catch (ArrayIndexOutOfBoundsException  e) {
			return obj;
		}
		return obj;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == 0) {
			return JLabel.class;
		} else {
			return Object.class;
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		FileListItem item = vecData.get(rowIndex);
		if( columnIndex == 0) {
			item.setFileLabel((JLabel)aValue);
		} else if( columnIndex == 1 ) {
			item.setFileUpdateDate((String)aValue);
		} else {
			item.setFileSize((String)aValue);
		}
    }
	
	private JLabel getFileLabel(File file) {
		JLabel label = new JLabel(file.getName());
		label.setIcon(CommonUtils.getSmallIcon(file));
		return label;
	}
	
	private String getFileUpdateDate(File file) {
		Date lastModified = new Date(file.lastModified());
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return fmt.format(lastModified);
	}

	private String getFileSize(File file) {
		if(file.isFile()) {
			return CommonUtils.getSize(file.length());
		} else {
			return "";
		}
	}

	public File[] getFiles() {
		return files;
	}

}
