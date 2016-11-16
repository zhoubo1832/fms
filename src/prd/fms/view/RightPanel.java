package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class RightPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static RightPanel instance; 
	 
	public RightPanel() {
		instance = this;
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		
	}
	
	public void show(File[] files) {

		if(files.length == 0 ) {
			this.removeAll();
			this.revalidate();
			this.repaint();
			return;
		}
		
		this.removeAll();
		
		
	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,ViewConstants.RIGHT_PANEL_HGAP,ViewConstants.RIGHT_PANEL_VGAP));
	    panel.setBackground(Color.WHITE);
	    panel.setPreferredSize(computePreferredSize(files.length));
	    
	    for(File file : files) {
	    	FilePanel fp = new FilePanel(file);
	    	panel.add(fp);
	    }
	    
	    JScrollPane scrollPane = new JScrollPane(panel);
	    scrollPane.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		scrollPane.setBorder(null);
		this.add(scrollPane,BorderLayout.CENTER);
		
		this.revalidate();
		this.repaint();
	}

	private Dimension computePreferredSize(int fileNum) {
		final int fw = ViewConstants.FILE_PANEL_WIDTH;
		final int fh = ViewConstants.FILE_PANEL_HEIGHT;
		
		final int hgap = ViewConstants.RIGHT_PANEL_HGAP;
		final int vgap = ViewConstants.RIGHT_PANEL_VGAP;
		
		int w = this.getWidth();	    
	    int colNum = (w - (w/fw + 1) * hgap)/fw;
	    int rowNum = (int) Math.ceil(fileNum/colNum);
	    int height = (rowNum*fh)+(rowNum+1)*vgap;
	    
		return new Dimension(this.getWidth(), height);
	}
}
