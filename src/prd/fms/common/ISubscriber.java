package prd.fms.common;

import javax.swing.tree.TreePath;

/**
 * <p>Subscriber interface.</p>
 * 
 * @author zhoubo
 * 
 */
public interface ISubscriber {

	/**
	 * <p>Update subscriber's status.</p>
	 * @param treePath  Tree path
	 */
	void update(TreePath treePath);
}
