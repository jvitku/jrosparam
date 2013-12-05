package ctu.nengoros.rosparam;


import org.apache.commons.logging.Log;
import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.parameter.ParameterTree;

/**
 * First, basic implementation of rosparam node. This just starts and waits for commands.
 * Assumed that the RosRunner is used, the RosRunner.start() uses awaitStart method, so 
 * we can start node and immediately call set, get().. methods.
 *  
 * @author Jaroslav Vitku
 *
 */
public class Rosparam extends AbstractNodeMain implements RosparamInt{

	Log l;
	private ParameterTree pt;
	private final int sleeptime = 50;
	private boolean coreRunning = false;	// assume that the core is running after onStart is called
	private ParameterTreeCrawler ptc;
	@Override
	public GraphName getDefaultNodeName() { return GraphName.of("jrosparam"); }

	@Override
	public void onStart(ConnectedNode connectedNode){
		this.coreRunning = true;
		l = connectedNode.getLog();
		pt = connectedNode.getParameterTree();
		ptc = new ParameterTreeCrawler(pt);
		
		// ROS uses these cancellable loops
		connectedNode.executeCancellableLoop(new CancellableLoop() {

			@Override
			protected void setup() {
				l.info("Rosparam node Launched! Waiting for commands..");
			}

			@Override
			protected void loop() throws InterruptedException {
				Thread.sleep(sleeptime);
			}
		});
	}

	@Override
	public boolean coreRunning() { return this.coreRunning; }

	@Override
	public void set(String key, String value) {
		l.debug("Setting key: "+key+" to String value: "+value);
		pt.set(key, value);
	}

	@Override
	public void set(String key, Integer value) {
		l.debug("Setting key: "+key+" to Integer value: "+value);
		pt.set(key, value);
	}

	@Override
	public void set(String key, Double value) {
		l.debug("Setting key: "+key+" to Double value: "+value);
		pt.set(key, value);
	}

	@Override
	public void set(String key, boolean value) {
		l.debug("Setting key: "+key+" to boolean value: "+value);
		pt.set(key, value);
	}

	
	
	@Override
	public String getString(String key) throws Exception {
		l.debug("Getting String by the key: "+key);
		return pt.getString(key);
	}

	@Override
	public Integer getInteger(String key) throws Exception {
		l.debug("Getting Integer by the key: "+key);
		return pt.getInteger(key);
	}

	@Override
	public Double getDouble(String key) throws Exception {
		l.debug("Getting Double by the key: "+key);
		return pt.getDouble(key);
	}

	@Override
	public Boolean getBoolean(String key) throws Exception {
		l.debug("Getting Boolean by the key: "+key);
		return pt.getBoolean(key);
	}

	@Override
	public void delete(String key){
		pt.delete(key);
	}

	@Override
	public String printTree() {
		// concurrency..
		while(ptc==null){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return ptc.getAll();
	}

	@Override
	public String getStringValofParam(String key) throws Exception {
		String val = ptc.getParam(GraphName.of(key));
		
		if(val==null){
			return "Not found";
		}
		return val;
	}
}
