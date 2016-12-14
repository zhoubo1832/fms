package prd.fms.executor;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import prd.fms.controller.BaseActionListener;
import prd.fms.util.DbUtils;
import prd.fms.view.TagDialog;
import prd.fms.view.ViewConstants;

public class TagDialogActionExecutor extends BaseActionListener{

	private TagDialog dialog;
	
	public TagDialogActionExecutor(TagDialog dialog) {
		this.dialog = dialog;
	}
	
	@Override
	protected void execute(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn.getText().equals(ViewConstants.COMMON_BUTTON_ADD) ) {
			StringTokenizer token = new StringTokenizer(dialog.getNewTagTf().getText(),",");
			while(token.hasMoreElements()) {
				JCheckBox chkBox = new JCheckBox((String)token.nextElement());
				chkBox.setSelected(true);
				dialog.getTagPanel().add(chkBox);
			}
			dialog.getTagPanel().revalidate();
			dialog.getTagPanel().repaint();
		} else if(btn.getText().equals(ViewConstants.COMMON_BUTTON_SAVE) ) {
			JPanel panel = dialog.getTagPanel();
			int tagNum = panel.getComponentCount();
			List<String> tagList = new ArrayList<String>();
			for(int i=0; i<tagNum; i++) {
				JCheckBox checkBox = (JCheckBox)panel.getComponent(i);
				if(checkBox.isSelected()) {
					tagList.add(checkBox.getText());
				}
			}
			
			DbUtils db = new DbUtils();
			db.updateFileTags(dialog.getFilePath(), tagList);
		} else if(btn.getText().equals(ViewConstants.COMMON_BUTTON_CANCEL) ) {
			dialog.dispose();
		} 
	}
}
