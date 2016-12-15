package prd.fms.thread;

import java.io.File;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyException;
import net.contentobjects.jnotify.JNotifyListener;
import prd.fms.model.TagModel;

public class FileNotifyThread extends Thread implements JNotifyListener {

	
	@Override
	public void run() {
		int mask = JNotify.FILE_RENAMED | JNotify.FILE_DELETED;
		File[] files = File.listRoots();
		int[] watchIds = new int[files.length];
		for(int i=0; i<files.length; i++) {
			try {
				watchIds[i] = JNotify.addWatch(files[i].getPath(), mask, true, this);
			} catch (JNotifyException e) {
				e.printStackTrace();
			}
		}
		try {
			synchronized(this) {
				this.wait();
			}
		} catch (InterruptedException e) {
			System.out.println("son thread interrupted...");
			for(int id : watchIds) {
				try {
					JNotify.removeWatch(id);
				} catch (JNotifyException e1) {
					e1.printStackTrace();
				}
				System.out.println("Removed watchid:" + id);
			}
		}
		System.out.println("son thread exited...");
	}

	@Override
	public void fileCreated(int arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fileDeleted(int wd, String rootPath, String name) {
		TagModel.deleteTaggedFile(rootPath + name);
		System.out.println("deleted " + rootPath + " : " + name);		
	}

	@Override
	public void fileModified(int arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
		TagModel.renameTaggedFile(rootPath + oldName, rootPath + newName);
		System.out.println("renamed " + rootPath + " : " + oldName + " -> " + newName);	
		
	}

	public static void main(String[] args) {
		Thread t = new FileNotifyThread();
		t.start();
		try {
			sleep(60000);
			t.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main thread exited...");
	}
}
