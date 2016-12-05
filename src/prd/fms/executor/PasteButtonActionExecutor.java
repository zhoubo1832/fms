package prd.fms.executor;

import java.awt.event.ActionEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import prd.fms.common.SelectedFileList;
import prd.fms.controller.BaseActionListener;
import prd.fms.model.FileNodeModel;
import prd.fms.model.FileSystemModel;
import prd.fms.model.TreeNodeModel;
import prd.fms.util.CommonUtils;
import prd.fms.view.MainFrame;
import prd.fms.view.MainTree;
import prd.fms.view.PasteAlertDialog;
import prd.fms.view.ProgressBarFrame;
import prd.fms.view.ViewConstants;

public class PasteButtonActionExecutor extends BaseActionListener{

	@Override
	protected void execute(ActionEvent e) {
		final String desPath = CommonUtils.getPath(MainTree.instance.getSelectionPath());
		ArrayList<String> list = SelectedFileList.getInstance().getPathList();
		final String[] srcPathArray = new String[list.size()];
		list.toArray(srcPathArray);
		int fileNum = FileSystemModel.fileCount(srcPathArray);
//		FileSystemModel.copyFiles(srcPathArray, desPath);
		
		final ProgressBarFrame progressBar = new ProgressBarFrame(0, fileNum);
		
		Runnable task = new Runnable(){

			@Override
			public void run() {
				for(final String file : srcPathArray) {
					if(new File(file).isDirectory()) {
						File fileDest = new File(FileSystemModel.getDestPath(file, desPath));
						if(fileDest.exists()) {
							if(fileDest.isDirectory()) {
							try {
								SwingUtilities.invokeAndWait(new Runnable(){

									@Override
									public void run() {
										PasteAlertDialog.setMsg(ViewConstants.PASTE_ALERT_MESSAGE_01);
										PasteAlertDialog.setFileName(file);
										new PasteAlertDialog(MainFrame.instance, true);
										
									}
									
								});
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
//								PasteAlertDialog.setMsg(ViewConstants.PASTE_ALERT_MESSAGE_01);
//								PasteAlertDialog.setFileName(file);
//								new PasteAlertDialog(MainFrame.instance, true);
							} else {
							try {
								SwingUtilities.invokeAndWait(new Runnable(){

									@Override
									public void run() {
										PasteAlertDialog.setMsg(ViewConstants.PASTE_ALERT_MESSAGE_02);
										PasteAlertDialog.setFileName(file);
										new PasteAlertDialog(MainFrame.instance, true);
										
									}
									
								});
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
//								PasteAlertDialog.setMsg(ViewConstants.PASTE_ALERT_MESSAGE_02);
//								PasteAlertDialog.setFileName(file);
//								new PasteAlertDialog(MainFrame.instance, true);
							}
						} else {
							FileSystemModel.copyDir(file, desPath, progressBar);
						}
					} else {
						File fileDest = new File(FileSystemModel.getDestPath(file, desPath));
						if(fileDest.exists()) {
							return;
						}
						FileSystemModel.copyFile(file, desPath, progressBar);
					}
					try {
						SwingUtilities.invokeAndWait(new Runnable(){

							@Override
							public void run() {
								progressBar.updateProgress();							
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
				
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					SwingUtilities.invokeAndWait(new Runnable(){

						@Override
						public void run() {
							progressBar.dispose();
							// refresh right panel
							FileNodeModel.refresh();
							
							// refresh tree node
							TreeNodeModel.refresh();
						}
						
					});
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				// refresh right panel
//				FileNodeModel.refresh();
//				
//				// refresh tree node
//				TreeNodeModel.refresh();
			}
			
		};
		
		
		Thread t = new Thread(task);
		t.start();
	}

}
