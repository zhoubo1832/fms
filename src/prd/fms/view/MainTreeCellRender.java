package prd.fms.view;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * <p>File tree cell render.</p>
 * 
 * @author zhoubo
 * 
 */
public class MainTreeCellRender extends DefaultTreeCellRenderer{

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
		
		if(node.isRoot()) {
			return this;
		}
		
		// set icon to folder
		this.setIcon(this.getClosedIcon());

		return this;
	}

}
