package prd.fms.executor;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import prd.fms.controller.BaseDocumentListener;
import prd.fms.model.FileNodeModel;
import prd.fms.thread.SearchFileThread;
import prd.fms.util.CommonUtils;
import prd.fms.view.RightPanel;

public class SearchTextFieldDocumentExecutor extends BaseDocumentListener{

	private JTextField jtf;
	
	public SearchTextFieldDocumentExecutor(JTextField jtf) {
		this.jtf = jtf;
	}
	
	@Override
	protected void executeInsert(DocumentEvent e) {
		execute(e);
	}

	@Override
	protected void executeRemove(DocumentEvent e) {
		execute(e);
	}
		
	private void execute(DocumentEvent e) {
		if(!jtf.hasFocus()) {
			return;
		}
		
		if(CommonUtils.getCurrentPath() == null) {
			return;
		}
		
		Document doc = e.getDocument();
		int len = doc.getLength();
		if(len > 0) {
			try {
				final String key = doc.getText(0, len).trim();
				
				if(key.length() > 0) {
					RightPanel.instance.clearContents();
					
					final String currentPath = CommonUtils.getCurrentPath();
					SearchFileThread thread = new SearchFileThread(key, currentPath);
					thread.start();

				} else {
					FileNodeModel.refresh();
				}
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		} else {
			FileNodeModel.refresh();
		}
	}
}
