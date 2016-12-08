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

import prd.fms.executor.ProgressBarFrameWindowExecutor;
import prd.fms.util.CommonUtils;

public class ProgressBarFrame extends JFrame{

	private JProgressBar progressBar;
	private JButton cancelButton;
	private int curValue;
	private int max;
	private Thread thread;
	
	private ProgressBarFrame instance;

	public ProgressBarFrame(int min, int max, final Thread thread){
		this.instance = this;
		this.curValue = min;
		this.max = max;
		this.thread = thread;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.addWindowListener(new ProgressBarFrameWindowExecutor(thread));
		
		progressBar = new JProgressBar(min, max);
		progressBar.setValue(min);
		progressBar.setStringPainted(true);
		
		cancelButton = new JButton(ViewConstants.COMMON_BUTTON_CANCEL);
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				instance.dispose();
			}
			
		});
		
		JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		cancelPanel.add(cancelButton);
		getContentPane().setLayout(new BorderLayout());
		add(progressBar,BorderLayout.CENTER);
		add(cancelPanel,BorderLayout.SOUTH);
		
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
