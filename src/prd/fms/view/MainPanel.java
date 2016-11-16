package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public MainPanel() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5, false));
		
		JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		mainSplit.setDividerLocation(200);
		mainSplit.add(new LeftPanel(),JSplitPane.LEFT,0);
		
		mainSplit.add(new RightPanel(),JSplitPane.RIGHT,0);
		this.add(new ToolbarPanel(), BorderLayout.NORTH);
		this.add(mainSplit,BorderLayout.CENTER);
		
//		System.out.println(rightPanel.getScrollPaneWidth());
	}
}
