package prd.fms.controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * <p>File panel's focus listener.</p>
 * 
 * @author zhoubo
 * 
 */
public class FilePanelFocusController implements FocusListener{

	@Override
	public void focusGained(FocusEvent e) {
//		JPanel panel = (JPanel)e.getSource();
//		panel.setBackground(new Color(219,243,146));
//		panel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
//		ToolbarPanel.instance.setRenameButtonEnabled(true);
	}

	@Override
	public void focusLost(FocusEvent e) {
//		boolean flgControl = FilePanelKeyController.controlKeyPressed;
//		boolean flgShift = FilePanelKeyController.shiftKeyPressed;
//		if( !flgControl && !flgShift) {
//			JPanel panel = (JPanel)e.getSource();
//			panel.setBorder(null);
//			panel.setBackground(null);
//		}
	}

}
