package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * <p>Main panel and main frame's content panel.</p>
 * 
 * @author zhoubo
 * 
 */
public class MainPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public MainPanel() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5, false));
		
		// create split pane
		JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		mainSplit.setDividerLocation(200);
		mainSplit.add(new LeftPanel(),JSplitPane.LEFT,0);		
		mainSplit.add(new RightPanel(),JSplitPane.RIGHT,0);
		
		// add tool bar to north
		this.add(new ToolbarPanel(), BorderLayout.NORTH);
		// add split pane to center
		this.add(mainSplit,BorderLayout.CENTER);
		// add information bar panel to south
		this.add(new InfoBarPanel(),BorderLayout.SOUTH);
	}
}
