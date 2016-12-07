package prd.fms.executor;

import java.awt.event.FocusEvent;

import javax.swing.JTextField;

import prd.fms.controller.BaseFocusListener;

public class SearchTextFieldFocusExecutor extends BaseFocusListener{
		
	@Override
	public void focusGained(FocusEvent e) {
		JTextField jtf = (JTextField)e.getSource();
		
		jtf.setText("");
	}

	@Override
	protected void executeLost(FocusEvent e) {
		JTextField jtf = (JTextField)e.getSource();
		if(jtf.getText().length() == 0) {
			jtf.setText("Search");
		}
		
	}
}
