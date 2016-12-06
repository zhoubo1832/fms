package prd.fms.executor;

import java.io.File;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import prd.fms.controller.BaseDocumentListener;
import prd.fms.model.FileNodeModel;
import prd.fms.model.FileSystemModel;
import prd.fms.util.CommonUtils;
import prd.fms.view.RightPanel;

public class SearchTextFieldDocumentExecutor extends BaseDocumentListener{

	@Override
	protected void executeInsert(DocumentEvent e) {
		if(CommonUtils.getCurrentPath() == null) {
			return;
		}
		List<File> list = doSearch(e);
		if(list == null) {
			FileNodeModel.refresh();
		} else {
			File[] files = new File[list.size()];
			list.toArray(files);
			RightPanel.instance.show(files);
		}
	}

	@Override
	protected void executeRemove(DocumentEvent e) {
		if(CommonUtils.getCurrentPath() == null) {
			return;
		}
		List<File> list = doSearch(e);
		if(list == null) {
			FileNodeModel.refresh();
		} else {
			File[] files = new File[list.size()];
			list.toArray(files);
			RightPanel.instance.show(files);
		}
	}
	
	private List<File> doSearch(DocumentEvent e) {
		List<File> list = null;
		Document doc = e.getDocument();
		int len = doc.getLength();
		if(len > 0) {
			try {
				String key = doc.getText(0, len);
				list = FileSystemModel.searchFile(key, CommonUtils.getCurrentPath());
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return list;
	}
}
