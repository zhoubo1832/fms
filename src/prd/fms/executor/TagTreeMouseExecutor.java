package prd.fms.executor;

import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import prd.fms.controller.BaseMouseListener;
import prd.fms.model.TagModel;
import prd.fms.view.RightPanel;

public class TagTreeMouseExecutor extends BaseMouseListener{

	@Override
	protected void executeClicked(MouseEvent e) {
		JTree jtree = (JTree)e.getSource();
		TreePath tp = jtree.getPathForLocation(e.getX(), e.getY());
		if(tp == null || tp.getParentPath() == null) {
			return;
		}
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tp.getLastPathComponent();
		if(node == null) {
			return;
		}
		
		String tagName = (String)node.getUserObject();
		List<String> fileList = TagModel.getTagFiles(tagName);
		int numFiles = fileList.size();
		if(numFiles == 0 ) {
			return;
		}
		
		File[] files = new File[numFiles];
		for(int i=0; i< numFiles; i++) {
			files[i] = new File(fileList.get(i));
		}
		RightPanel.instance.show(files);
	}
}
