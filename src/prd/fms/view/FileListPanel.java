package prd.fms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import prd.fms.bean.DirNode;
import prd.fms.controller.FileTableMouseController;

public class FileListPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private JTable fileTable;
	
	public FileListPanel(File[] files) {
		fileTable = new JTable(new FileListTableModel(files));
		fileTable.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				JLabel iconLabel = (JLabel)value;
				
				if(isSelected) {
					iconLabel.setOpaque(true);
					iconLabel.setBackground(fileTable.getSelectionBackground());
				} else {
						iconLabel.setOpaque(true);
						iconLabel.setBackground(Color.white);
				}
				
				return (Component) iconLabel;
			}
		});
		
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
	}
}
