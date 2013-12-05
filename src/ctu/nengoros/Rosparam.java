package ctu.nengoros;

import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;

public class Rosparam extends AbstractNodeMain{

	@Override
	public GraphName getDefaultNodeName() { return GraphName.of("jrosparam"); }

	@Override
	public void onStart(ConnectedNode connectedNode){
		System.out.println("HW!");
		
	}

	
}
