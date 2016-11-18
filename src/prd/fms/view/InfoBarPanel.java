package prd.fms.view;

import java.awt.FlowLayout;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InfoBarPanel extends JPanel{
	
	private JLabel infoLabel;
	private static final String ITEMS = " items";
	private static final String UPDATE_DATE = "       update date: ";
	private static final String FILE_SIZE = "       size: ";
	
	public static InfoBarPanel instance;
	
	public InfoBarPanel() {
		super(new FlowLayout(FlowLayout.LEFT));
		instance = this;
		infoLabel = new JLabel(ViewConstants.INFO_LABEL_DEFAULT_TEXT);
		this.add(infoLabel);
	}
	
	public void setNodeInfoLabel(String path) {
		if(path == null) {
			infoLabel.setIcon(null);
			infoLabel.setText(ViewConstants.INFO_LABEL_DEFAULT_TEXT);
			return;
		}
		
		File file = new File(path);
		infoLabel.setIcon(FilePanel.getBigIcon(file));
		
		infoLabel.setText(String.valueOf(file.list().length) + ITEMS);
		
	}
	
	public void setFileInfoLabel(String path) {
		if(path == null) {
			infoLabel.setIcon(null);
			infoLabel.setText(ViewConstants.INFO_LABEL_DEFAULT_TEXT);
			return;
		}
		
		File file = new File(path);
		infoLabel.setIcon(FilePanel.getBigIcon(file));
		Date lastModified = new Date(file.lastModified());
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		if(file.isFile()) {
			infoLabel.setText(file.getName() + UPDATE_DATE + fmt.format(lastModified) + FILE_SIZE + getSize(file.length())) ;
		} else {
			infoLabel.setText(file.getName() + UPDATE_DATE + fmt.format(lastModified));
		}
		
	}
	
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
