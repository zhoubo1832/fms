package prd.fms.view;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import prd.fms.controller.FileTableMouseController;

public class FileListPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private JTable fileTable;

	public JTable getFileTable() {
		return fileTable;
	}

	public static FileListPanel instance;
	
	public FileListPanel(File[] files) {
		fileTable = new JTable(new FileListTableModel(files));
		fileTable.getColumnModel().getColumn(0).setCellRenderer(new FileListTableRender(fileTable));
		
		fileTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
//				System.out.println("First:" + e.getFirstIndex());
//				System.out.println("Last:" + e.getLastIndex());
//				
//				int i = fileTable.getSelectedRow();
//				JLabel iconLabel = (JLabel)fileTable.getValueAt(i, 0);
//				iconLabel.setOpaque(true);
//				iconLabel.setBackground(fileTable.getSelectionBackground());
								
			}});
		
		fileTable.addMouseListener(new FileTableMouseController(this.fileTable));
		setLayout(new BorderLayout());
		add(fileTable.getTableHeader(),BorderLayout.PAGE_START);
		add(fileTable,BorderLayout.CENTER);
		instance = this;
	}
}
