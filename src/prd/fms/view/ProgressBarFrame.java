package prd.fms.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import prd.fms.util.CommonUtils;

public class ProgressBarFrame extends JFrame{

	private JProgressBar progressBar;
	
	private int curValue;
	private int max;
	
	public ProgressBarFrame(int min, int max){
		this.curValue = min;
		this.max = max;
		
		this.setUndecorated(true);
		
//		super(new BorderLayout());
		progressBar = new JProgressBar(min, max);
		progressBar.setValue(min);
		progressBar.setStringPainted(true);
		
		getContentPane().setLayout(new BorderLayout());
		add(progressBar,BorderLayout.CENTER);
		
//		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(200, 20);
		CommonUtils.setScreenCenter(this);
		this.setVisible(true);
	}
	
	public void updateProgress() {
		++curValue;
		if(curValue > max) {
			curValue = max;
		}
		progressBar.setValue(curValue);
//		if(curValue == max) {
//			try {
//				Thread.currentThread().sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			this.dispose();
//		}
	}
}
