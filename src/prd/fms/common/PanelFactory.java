package prd.fms.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JPanel;

import prd.fms.executor.ParentFilePanelMouseExecutor;
import prd.fms.view.FileListPanel;
import prd.fms.view.FilePanel;
import prd.fms.view.ToolbarPanel;
import prd.fms.view.ViewConstants;

public class PanelFactory {

	public static JPanel getPanel(JPanel panel, File[] files) {
		int index = ToolbarPanel.instance.getViewList().getSelectedIndex();
		if(index == 0) {
			// create panel to contain all file panels
		    JPanel parentFilePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,ViewConstants.RIGHT_PANEL_HGAP,ViewConstants.RIGHT_PANEL_VGAP));
		    parentFilePanel.setBackground(Color.WHITE);
		    parentFilePanel.setPreferredSize(computePreferredSize(panel, files.length));
		    parentFilePanel.addMouseListener(new ParentFilePanelMouseExecutor());
		    
		    for(File file : files) {
		    	// create file panel
		    	FilePanel fp = new FilePanel(file);
		    	parentFilePanel.add(fp);
		    }
		    return parentFilePanel;
		} else {
			// create file list panel
			FileListPanel fileListPanel = new FileListPanel(files);
			// add table header
			panel.add(fileListPanel.getFileTable().getTableHeader(), BorderLayout.NORTH);
			return fileListPanel;
		}
	}
	
	/**
	 * <p>Compute preferred size.</p>
	 * @param panel    Right panel
	 * @param fileNum  Number of files
	 * @return Width and height
	 */
	public static Dimension computePreferredSize(JPanel panel, int fileNum) {
		final int fw = ViewConstants.FILE_PANEL_WIDTH;
		final int fh = ViewConstants.FILE_PANEL_HEIGHT;
		
		final int hgap = ViewConstants.RIGHT_PANEL_HGAP;
		final int vgap = ViewConstants.RIGHT_PANEL_VGAP;
		
		int w = panel.getWidth();	    
	    int colNum = (w - (w/fw + 1) * hgap)/fw;
	    int rowNum = (int) Math.ceil(fileNum/colNum);
	    int height = (rowNum*fh)+(rowNum+1)*vgap;
	    
		return new Dimension(w, height);
	}
}
