package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <p>Paste alert dialog.</p>
 * 
 * @author zhoubo
 * 
 */
public class PasteAlertDialog extends CommonDialog{

	private static final long serialVersionUID = 1L;
	
	private JLabel msgLabel;

	private JButton okBtn;
	
	private static String msg;
	
	public static void setMsg(String msg) {
		PasteAlertDialog.msg = msg;
	}

	private static String fileName;
	
	public static void setFileName(String fileName) {
		PasteAlertDialog.fileName = fileName;
	}

	public PasteAlertDialog(Frame parent, boolean modal) {
		super(parent, ViewConstants.PASTE_ALERT_DIALOG_TITLE, modal);
		
	}

	@Override
	protected void setUI() {
		this.setSize(ViewConstants.PASTE_DIALOG_WIDTH, ViewConstants.PASTE_DIALOG_HEIGHT);
		
		this.setLayout(new BorderLayout());
		
		msgLabel = new JLabel(msg);
		JPanel msgPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		msgPanel.add(msgLabel);
		
		JLabel fileLabel = new JLabel(fileName);
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
	}
}
