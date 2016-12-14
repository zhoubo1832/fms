package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EtchedBorder;

import prd.fms.model.TagModel;

/**
 * <p>Left panel used to contain file tree.</p>
 * 
 * @author zhoubo
 * 
 */
public class LeftPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	public LeftPanel() {
		this.setPreferredSize(new Dimension(200,100));
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		// create scroll pane to contain file tree
		JScrollPane scrollPane = new JScrollPane(new MainTree(),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
		JSplitPane leftSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		leftSplit.setDividerLocation(300);
		leftSplit.add(scrollPane, JSplitPane.TOP, 0);
		
		JScrollPane tagPane = new JScrollPane(new TagTree(),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		leftSplit.add(tagPane, JSplitPane.BOTTOM, 0);
		
		// add scroll pane
		this.add(leftSplit);
	}
}
