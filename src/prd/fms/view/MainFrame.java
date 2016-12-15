package prd.fms.view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import prd.fms.executor.MainFrameWindowExecutor;
import prd.fms.thread.FileNotifyThread;

/**
 * <p>Main frame and application's entry.</p>	
 * 
 * @author zhoubo
 * 
 */
public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public static MainFrame instance;
	
	public MainFrame(Thread t) {
		this.setContentPane(new MainPanel());
		this.setTitle(ViewConstants.MAIN_FRAME_TITLE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new MainFrameWindowExecutor(t));
		this.setVisible(true);
		
	}
	
	public static void main(String args[]) {
		final Thread t = new FileNotifyThread();
		t.start();
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				try {
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				
				// create main frame
				instance = new MainFrame(t);
		}});
	}
}
