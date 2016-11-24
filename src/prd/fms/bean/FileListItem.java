package prd.fms.bean;

import javax.swing.JLabel;

public class FileListItem {
	private JLabel fileLabel;
	private String fileUpdateDate;
	private String fileSize;
	public JLabel getFileLabel() {
		return fileLabel;
	}
	public void setFileLabel(JLabel fileLabel) {
		this.fileLabel = fileLabel;
	}
	
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileUpdateDate() {
		return fileUpdateDate;
	}
	public void setFileUpdateDate(String fileUpdateDate) {
		this.fileUpdateDate = fileUpdateDate;
	}
}
