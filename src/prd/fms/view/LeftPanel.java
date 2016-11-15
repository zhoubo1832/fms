package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

public class LeftPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LeftPanel() {
		this.setPreferredSize(new Dimension(200,100));
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		JScrollPane scrollPane = new JScrollPane(new MainTree(),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scrollPane);
	}
}
