package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.Color;
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

/**
 * <p>File panel to display icon and file name.</p>
 * 
 * @author zhoubo
 * 
 */
public class FilePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private static FilePanelMouseController filePanelMouseController = new FilePanelMouseController();
	private static FilePanelFocusController filePanelFocusController = new FilePanelFocusController();
	
	/**
	 * <p>File object.</p>
	 */
	private File file;
	
	public static Icon getBigIcon(File f) {  
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
	 
	public FilePanel(File file) {
		super(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.file = file;
		
		// create icon label
		JLabel iconLabel = new JLabel("",SwingConstants.CENTER);
		iconLabel.setIcon(getBigIcon(file));
		add(iconLabel,BorderLayout.NORTH);
		
		// create file name label
		JLabel fileNameLabel = new JLabel(file.getName(),SwingConstants.CENTER);
		fileNameLabel.setToolTipText(file.getName());
		add(fileNameLabel,BorderLayout.CENTER);
		
		setPreferredSize(new Dimension(ViewConstants.FILE_PANEL_WIDTH,ViewConstants.FILE_PANEL_HEIGHT));
		// add mouse listener
		addMouseListener(filePanelMouseController);
		// add focus listener
		addFocusListener(filePanelFocusController);

	}

	public File getFile() {
		return this.file;
	}
}
