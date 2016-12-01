package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * <p>Alert dialog.</p>
 * 
 * @author zhoubo
 * 
 */
public class AlertDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private JLabel msgLabel;

	private JButton okBtn;
	
	public AlertDialog(Frame parent, boolean modal, String msg, String file) {
		this.setTitle(ViewConstants.COMMON_ALERT_DIALOG_TITLE);
		this.setSize(ViewConstants.PASTE_DIALOG_WIDTH, ViewConstants.PASTE_DIALOG_HEIGHT);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int screenWidth = dim.width;
		int screenHeight = dim.height;
		this.setLocation(screenWidth/2 - this.getWidth()/2, screenHeight/2 - this.getHeight()/2);
		
		this.setLayout(new BorderLayout());
		
		msgLabel = new JLabel(msg);
		JPanel msgPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		msgPanel.add(msgLabel);
		
		JLabel fileLabel = new JLabel(file);
		JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		filePanel.add(fileLabel);
		
		okBtn = new JButton(ViewConstants.COMMON_BUTTON_OK);
		okBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		JPanel okPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		okPanel.add(okBtn);
		
		add(msgPanel, BorderLayout.NORTH);
		add(filePanel, BorderLayout.CENTER);
		add(okPanel, BorderLayout.SOUTH);
		
		this.setAlwaysOnTop(true);
		this.requestFocus();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
}
