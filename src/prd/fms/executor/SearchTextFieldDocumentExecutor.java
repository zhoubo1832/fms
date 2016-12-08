package prd.fms.executor;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import prd.fms.controller.BaseDocumentListener;
import prd.fms.model.FileNodeModel;
import prd.fms.model.FileSystemModel;
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
					final String currentPath = CommonUtils.getCurrentPath();
					Runnable runnable = new Runnable(){

						@Override
						public void run() {
							List<File> list = null;
							list = FileSystemModel.searchFile(key, currentPath);
							
							if(list == null) {
								try {
									SwingUtilities.invokeAndWait(new Runnable(){

										@Override
										public void run() {
											FileNodeModel.refresh();										
										}
										
									});
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							} else {
								final File[] files = new File[list.size()];
								list.toArray(files);
								try {
									SwingUtilities.invokeAndWait(new Runnable(){

										@Override
										public void run() {
											RightPanel.instance.show(files);
											
										}
										
									});
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
						}
						
					};
					
					Thread t = new Thread(runnable);
					t.start();
				}
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
}
