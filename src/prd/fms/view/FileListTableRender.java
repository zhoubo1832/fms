package prd.fms.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class FileListTableRender implements TableCellRenderer{
	private JTable fileTable;
	
	public FileListTableRender(JTable fileTable) {
		this.fileTable = fileTable;
	}
	
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

}
