package prd.fms.view;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JFrame;

import prd.fms.util.CommonUtils;

public abstract class CommonDialog extends JDialog{

	private static final long serialVersionUID = 1L;

	public CommonDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		init();
	}
	
	protected abstract void setUI();
	
	private void init() {
		setUI();
		CommonUtils.setScreenCenter(this);
		this.setAlwaysOnTop(true);
		this.requestFocus();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
