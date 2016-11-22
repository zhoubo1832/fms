package prd.fms.common;

/**
 * <p>Publisher interface.</p>
 * 
 * @author zhoubo
 * 
 */
public interface IPublisher {
	
	/**
	 * <p>Add subscriber to subscriber list.</p>
	 * @param sub  Subscriber
	 */
	void add(ISubscriber sub);
	
	/**
	 * <p>Publish message to all subscribers.</p>
	 */
	void send();
}
