package prd.fms.view;

import java.awt.BorderLayout;
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
		this.setLayout(new BorderLayout());
		
	}
	
	public void show(File[] files) {
	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    panel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));

	    for(File file : files) {
	    	panel.add(new FilePanel(file));
	    }
	    
	    JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBorder(null);
		this.add(scrollPane,BorderLayout.CENTER);
		
		this.revalidate();
		this.repaint();
	}

}
