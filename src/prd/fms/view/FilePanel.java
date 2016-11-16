package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import prd.fms.controller.FilePanelFocusController;
import prd.fms.controller.FilePanelMouseController;

public class FilePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static FilePanelMouseController filePanelMouseController = new FilePanelMouseController();
	private static FilePanelFocusController filePanelFocusController = new FilePanelFocusController();
	
	private File file;
	
	private static Icon getBigIcon(File f) {  
        if (f!=null && f.exists()) {  
            try {  
                sun.awt.shell.ShellFolder sf = sun.awt.shell.ShellFolder.getShellFolder(f);  
                return new ImageIcon(sf.getIcon(true));  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
	
//	 private static Icon getSmallIcon(File f) {  
//	        if (f != null && f.exists()) {  
//	            FileSystemView fsv = FileSystemView.getFileSystemView();  
//	            return fsv.getSystemIcon(f);  
//	        }  
//	        return null;  
//	 }  
	 
	public FilePanel(File file) {
		super(new BorderLayout());
		this.file = file;
		
		JLabel iconLabel = new JLabel("",SwingConstants.CENTER);
		iconLabel.setIcon(getBigIcon(file));

		add(iconLabel,BorderLayout.NORTH);
		JLabel fileNameLabel = new JLabel(file.getName(),SwingConstants.CENTER);
		fileNameLabel.setToolTipText(file.getName());
		
		add(fileNameLabel,BorderLayout.CENTER);
		setPreferredSize(new Dimension(ViewConstants.FILE_PANEL_WIDTH,ViewConstants.FILE_PANEL_HEIGHT));
		
		addMouseListener(filePanelMouseController);

		addFocusListener(filePanelFocusController);

	}

	public File getFile() {
		return this.file;
	}
}
