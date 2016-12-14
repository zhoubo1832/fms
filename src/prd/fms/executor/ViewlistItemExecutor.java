package prd.fms.executor;

import java.io.File;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import prd.fms.controller.BaseItemListener;
import prd.fms.model.FileNodeModel;
import prd.fms.model.TagModel;
import prd.fms.util.CommonUtils;
import prd.fms.view.RightPanel;
import prd.fms.view.TagTree;

public class ViewlistItemExecutor extends BaseItemListener{

	@Override
	protected void execute() {
		if(TagTree.tagClicked) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) TagTree.instance.getSelectionPath().getLastPathComponent();
			
			String tagName = (String)node.getUserObject();
			List<String> fileList = TagModel.getTagFiles(tagName);
			File[] files = CommonUtils.convertToFileArray(fileList);
			
			if(files != null) {
				RightPanel.instance.show(files);
			}
		} else {
			FileNodeModel.refresh();
		}
	}

}
