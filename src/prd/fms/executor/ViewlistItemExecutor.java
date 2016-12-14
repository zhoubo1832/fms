package prd.fms.executor;

import java.io.File;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import prd.fms.controller.BaseItemListener;
import prd.fms.model.FileNodeModel;
import prd.fms.model.TagModel;
import prd.fms.view.RightPanel;
import prd.fms.view.TagTree;

public class ViewlistItemExecutor extends BaseItemListener{

	@Override
	protected void execute() {
		if(TagTree.tagClicked) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) TagTree.instance.getSelectionPath().getLastPathComponent();
			
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
		} else {
			FileNodeModel.refresh();
		}
	}

}
