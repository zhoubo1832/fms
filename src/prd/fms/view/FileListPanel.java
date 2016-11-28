package prd.fms.view;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

import prd.fms.controller.FileTableMouseController;

/**
 * <p>File list panel to display icon,file name,updated date and size.</p>
 * 
 * @author zhoubo
 * 
 */
public class FileListPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private JTable fileTable;

	public JTable getFileTable() {
		return fileTable;
	}

	public static FileListPanel instance;
	
	public FileListPanel(File[] files) {
		
		// create file table using customized FileListTableModel
		FileListTableModel model = new FileListTableModel(files);
		fileTable = new JTable(model);
		
		SortedFileListTableModel sortedModel = new SortedFileListTableModel(model);
		TableRowSorter<SortedFileListTableModel> sorter = new TableRowSorter<SortedFileListTableModel>(sortedModel);
		fileTable.setRowSorter(sorter);
		
		// set cell renderer for first column
		fileTable.getColumnModel().getColumn(0).setCellRenderer(new FileListTableRender(fileTable));
		
		// add listener
		fileTable.addMouseListener(new FileTableMouseController(this.fileTable));
		
		setLayout(new BorderLayout());
		// add table body
		add(fileTable,BorderLayout.CENTER);
		instance = this;
	}
}
