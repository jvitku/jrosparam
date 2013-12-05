package ctu.nengoros;

import org.ros.node.NodeMain;

/**
 * This is runnable application which implements subset of 
 * functionalities of the original rosparam application
 * @see http://wiki.ros.org/rosparam
 * 
 * Supported commands are:
 * -rosparam set <key> <val>
 * -rosparam get <key>
 * -rosparam list
 * -rosparam delete <key>
 * 
 * @author Jaroslav Vitku
 *
 */
public class Jrosparam {

	static final String note = "jrosparam, a Java implementation of rosparam is a command-line tool" +
			"for getting, setting and deleting parameters from the ROS" +
			"Parameter Server.";
	
	static final String cmds = "Commands:\n" +
			"\tjrosparam set\t set parameter\n"+
			"\tjrosparam get\t get parameter\n"+
			"\tjrosparam load\t load parameters from file\t[NOT SUPPORTED]\n"+
			"\tjrosparam dump\t dump parameters to file\t[NOT SUPPORTED]\n"+
			"\tjrosparam delete delete parameter\n"+
			"\tjrosparam list\t list parameter names";
	
	public static void main(String[] args){
		if(!argsOK(args))
			return;
		
		RosRunner r;
		try {
			r = new RosRunner("ctu.nengoros.Rosparam");
		} catch (Exception e) {
			System.err.println("ERROR: could not start the rosparam node! Roscore launched??");
			e.printStackTrace();
			return;
		}
		r.start();
		NodeMain node = r.getNode();
		
		
		
		
	}
	
	private static boolean argsOK(String[] args){
		if(args.length==0){
			printUsage();
			return false;
		}else if(args.length>3){
			System.err.println("ERROR: too many arguments specified");
			printUsage();
			return false;
		}
		return true;
	}
	
	private static void printUsage(){
		System.out.println(note+"\n");
		System.out.println(cmds);
	}
	
	
}
