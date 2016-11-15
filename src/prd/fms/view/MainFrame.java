package prd.fms.view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		this.setContentPane(new MainPanel());
		this.setTitle(ViewConstants.MAIN_FRAME_TITLE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {
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
				
				new MainFrame();
		}});
	}
}
