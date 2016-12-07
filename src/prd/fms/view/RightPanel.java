package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import prd.fms.common.PanelFactory;
import prd.fms.executor.RightPanelComponentExecutor;

/**
 * <p>Right panel to display all folders and files.</p>
 * 
 * @author zhoubo
 * 
 */
public class RightPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JPanel parentFilePanel;


	/**
	 * <p>RightPanel object itself.</p>
	 */
	public static RightPanel instance; 
	 
	public RightPanel() {
		instance = this;
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.addComponentListener(new RightPanelComponentExecutor());
	}
	
	/**
	 * <p>Display all folders and files.</p>
	 * @param files
	 */
	public void show(File[] files) {

		if(files.length == 0 ) {
			this.removeAll();
			this.revalidate();
			this.repaint();
			return;
		}
		
		this.removeAll();

		// create panel that contains all files and folders
		parentFilePanel = PanelFactory.getPanel(this, files);
		// create scroll pane to contain panel
	    JScrollPane scrollPane = new JScrollPane(parentFilePanel);
	    scrollPane.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		scrollPane.setBorder(null);
		this.add(scrollPane,BorderLayout.CENTER);
		
		this.revalidate();
		this.repaint();
	}

	public JPanel getParentFilePanel() {
		return parentFilePanel;
	}

}
