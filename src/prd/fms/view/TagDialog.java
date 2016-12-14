package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import prd.fms.bean.TagBean;
import prd.fms.executor.TagDialogActionExecutor;

public class TagDialog extends CommonDialog{

	private JTextField newTagTf;
	private JButton addTagBtn;
	private JButton saveTagBtn;
	private JButton cancelBtn;
	private JPanel tagPanel;
	
	private JPanel getTopPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newTagTf = new JTextField(20);
		addTagBtn = new JButton(ViewConstants.COMMON_BUTTON_ADD);
		saveTagBtn = new JButton(ViewConstants.COMMON_BUTTON_SAVE);
		cancelBtn = new JButton(ViewConstants.COMMON_BUTTON_CANCEL);
				
		addTagBtn.addActionListener(new TagDialogActionExecutor(this));
		saveTagBtn.addActionListener(new TagDialogActionExecutor(this));
		cancelBtn.addActionListener(new TagDialogActionExecutor(this));
		panel.add(newTagTf);
		panel.add(addTagBtn);
		panel.add(saveTagBtn);
		panel.add(cancelBtn);
		return panel;
	}
	
	private JPanel getBottomPanel() {
		tagPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JCheckBox chkBox = null;
		for(Object s : list) {
			TagBean tagBean = (TagBean)s;
			chkBox = new JCheckBox(tagBean.getTagName());
			chkBox.setSelected(tagBean.isSelected());
			tagPanel.add(chkBox);
		}
		
		return tagPanel;
	}
	
	public TagDialog(Frame owner, String title, boolean modal, String filePath, List<Object> list) {
		super(owner, title, modal, filePath, list);
		
	}

	@Override
	protected void setUI() {
		this.setSize(ViewConstants.TAG_DIALOG_WIDTH, ViewConstants.TAG_DIALOG_HEIGHT);
		
		this.add(getTopPanel(), BorderLayout.NORTH);
		this.add(getBottomPanel(), BorderLayout.CENTER);
		
	}

	public JTextField getNewTagTf() {
		return newTagTf;
	}

	public JPanel getTagPanel() {
		return tagPanel;
	}

	public String getFilePath() {
		return filePath;
	}

}
