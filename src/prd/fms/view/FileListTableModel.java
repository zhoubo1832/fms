package prd.fms.view;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;

public class FileListTableModel extends AbstractTableModel{

	private File[] files;
	
	public FileListTableModel() {
		
	}
	
	public FileListTableModel(File[] files) {
		this.files = files;
	}
	
	public void setFiles(File[] files) {
		this.files = files;
	}

	@Override
	public int getRowCount() {
		return files.length;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		File file = files[rowIndex];
		if(columnIndex == 0) {
			JLabel label = new JLabel(file.getName());
			label.setIcon(getSmallIcon(file));
			return label;
		} else if(columnIndex == 1) {
			Date lastModified = new Date(file.lastModified());
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			return fmt.format(lastModified);
		} else {
			if(file.isFile()) {
				return getSize(file.length());
			} else {
				return "";
			}
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == 0) {
			return JLabel.class;
		} else {
			return Object.class;
		}
	}

	private static Icon getSmallIcon(File f) {  
	    if (f != null && f.exists()) {  
	        FileSystemView fsv = FileSystemView.getFileSystemView();  
	        return fsv.getSystemIcon(f);  
	    }  
	    return null;  
	}
	
	/**
	 * <p>Get size string.</p>
	 * @param size  File size(byte)
	 * @return size string(byte/KB/MB/GB)
	 */
	private String getSize(long size) {
		if(size < 1024) {
			return size + " byte";
		} else if(size < 1024*1024) {
			return size/1024 + " KB";
		} else if(size < 1024*1024*1024) {
			return size/1024/1024 + " MB";
		} else if(size < (1024l*1024l*1024l*1024l)) {
			return size/1024/1024/1024 + " GB";
		} else {
			return size + " byte";
		}
	}
}
