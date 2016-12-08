package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import prd.fms.util.CommonUtils;

public class ProgressBarFrame extends JFrame{

	private JProgressBar progressBar;
	private JButton cancelButton;
	private int curValue;
	private int max;
	private Thread thread;
	
	private ProgressBarFrame instance;
	
	public Thread getThread() {
		return thread;
	}

	private boolean isCancelled = false;
	
	public boolean isCancelled() {
		return isCancelled;
	}

	public ProgressBarFrame(int min, int max, final Thread thread){
		this.instance = this;
		this.curValue = min;
		this.max = max;
		this.thread = thread;
//		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				thread.interrupt();
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
//		super(new BorderLayout());
		progressBar = new JProgressBar(min, max);
		progressBar.setValue(min);
		progressBar.setStringPainted(true);
		
		cancelButton = new JButton(ViewConstants.COMMON_BUTTON_CANCEL);
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				isCancelled = true;
				thread.interrupt();
				instance.dispose();
			}
			
		});
		
		JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		cancelPanel.add(cancelButton);
		getContentPane().setLayout(new BorderLayout());
		add(progressBar,BorderLayout.CENTER);
		add(cancelPanel,BorderLayout.SOUTH);
		
//		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(200, 100);
		CommonUtils.setScreenCenter(this);
		this.setVisible(true);
	}
	
	public void updateProgress() {
		++curValue;
		if(curValue > max) {
			curValue = max;
		}
		progressBar.setValue(curValue);
		if(curValue == max) {			
			this.dispose();
		}
	}
}
