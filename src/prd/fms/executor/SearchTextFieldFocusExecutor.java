package prd.fms.executor;

import java.awt.event.FocusEvent;

import javax.swing.JTextField;

import prd.fms.controller.BaseFocusListener;
import prd.fms.view.ViewConstants;

public class SearchTextFieldFocusExecutor extends BaseFocusListener{
	private boolean isSearch = true;
	
	@Override
	public void focusGained(FocusEvent e) {
		if(isSearch) {
			JTextField jtf = (JTextField)e.getSource();
			jtf.setText("");
			isSearch = false;
		}
	}

	@Override
	protected void executeLost(FocusEvent e) {
		JTextField jtf = (JTextField)e.getSource();
		if(jtf.getText().length() == 0) {
			jtf.setText(ViewConstants.SEARCH_HINT);
			isSearch = true;
		}
		
	}
}
