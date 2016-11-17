package prd.fms.view;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InfoBarPanel extends JPanel{
	
	private JLabel infoLabel;
	private static final String ITEMS = " items"; 
	public static InfoBarPanel instance;
	
	public InfoBarPanel() {
		super(new FlowLayout(FlowLayout.LEFT));
		instance = this;
		infoLabel = new JLabel(ViewConstants.INFO_LABEL_DEFAULT_TEXT);
		this.add(infoLabel);
	}
	
	public void setInfoLabel(String path) {
		if(path == null) {
			infoLabel.setIcon(null);
			infoLabel.setText(ViewConstants.INFO_LABEL_DEFAULT_TEXT);
			return;
		}
		
		File file = new File(path);
		infoLabel.setIcon(FilePanel.getBigIcon(file));
		
		infoLabel.setText(String.valueOf(file.list().length) + ITEMS);
		
	}
}
