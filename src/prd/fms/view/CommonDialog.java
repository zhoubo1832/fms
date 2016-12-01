package prd.fms.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

public abstract class CommonDialog extends JDialog{

	private static final long serialVersionUID = 1L;

	public CommonDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		init();
	}
	
	protected abstract void setUI();
	
	private void init() {
		setUI();
		setScreenCenter();
		this.setAlwaysOnTop(true);
		this.requestFocus();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void setScreenCenter() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int screenWidth = dim.width;
		int screenHeight = dim.height;
		this.setLocation(screenWidth/2 - this.getWidth()/2, screenHeight/2 - this.getHeight()/2);
	}
}
