package prd.fms.executor;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.tree.TreePath;

import prd.fms.controller.BaseMouseListener;
import prd.fms.util.CommonUtils;
import prd.fms.view.InfoBarPanel;
import prd.fms.view.MainFrame;
import prd.fms.view.ToolbarPanel;

public class TreeNodeMouseExecutor extends BaseMouseListener{

	
	@Override
	protected void executeClicked(final MouseEvent e) {
		MainFrame.instance.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				JTree jtree = (JTree)e.getSource();
				TreePath tp = jtree.getPathForLocation(e.getX(), e.getY());
				if(tp == null) {
					return null;
				}
				String path = CommonUtils.getPath(tp);
				// display node's detailed information in information panel
				InfoBarPanel.instance.setNodeInfoLabel(path);
				
				ToolbarPanel.instance.setRenameButtonEnabled(false);
				ToolbarPanel.instance.setNewfolderButtonEnabled(true);
				return null;
			}
			
			@Override
			protected void done() {
				MainFrame.instance.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			
		}.execute();
		
/*		
		JTree jtree = (JTree)e.getSource();
		TreePath tp = jtree.getPathForLocation(e.getX(), e.getY());
		if(tp == null) {
			return;
		}
		String path = CommonUtils.getPath(tp);
		// display node's detailed information in information panel
		InfoBarPanel.instance.setNodeInfoLabel(path);
		
		ToolbarPanel.instance.setRenameButtonEnabled(false);
		ToolbarPanel.instance.setNewfolderButtonEnabled(true);
*/

	}

}