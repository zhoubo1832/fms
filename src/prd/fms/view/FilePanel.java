package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import prd.fms.executor.FilePanelKeyExecutor;
import prd.fms.executor.FilePanelMouseExecutor;
import prd.fms.util.CommonUtils;

/**
 * <p>File panel to display icon and file name.</p>
 * 
 * @author zhoubo
 * 
 */
public class FilePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private static FilePanelMouseExecutor filePanelMouseController = new FilePanelMouseExecutor();
	private static FilePanelKeyExecutor filePanelKeyController = new FilePanelKeyExecutor();
	
	/**
	 * <p>File object.</p>
	 */
	private File file;  
	 
	public FilePanel(File file) {
		super(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.file = file;
		
		// create icon label
		JLabel iconLabel = new JLabel("",SwingConstants.CENTER);
		iconLabel.setIcon(CommonUtils.getBigIcon(file));
		add(iconLabel,BorderLayout.NORTH);
		
		// create file name label
		JLabel fileNameLabel = new JLabel(file.getName(),SwingConstants.CENTER);
		fileNameLabel.setToolTipText(file.getName());
		add(fileNameLabel,BorderLayout.CENTER);
		
		setPreferredSize(new Dimension(ViewConstants.FILE_PANEL_WIDTH,ViewConstants.FILE_PANEL_HEIGHT));
		// add mouse listener
		addMouseListener(filePanelMouseController);
		
		addKeyListener(filePanelKeyController);

	}

	public File getFile() {
		return this.file;
	}
}
