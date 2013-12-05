package ctu.nengoros;

/**
 * Interface for a rosparam class, @see: http://wiki.ros.org/rosparam
 * 
 * Supported data types so far:
 * string: 'foo'
 * integer: 1234
 * float: 1234.5
 * boolean: true
 * 
 * @author Jaroslav Vitku
 *
 */
public interface RosparamInt {
	
	public boolean coreRunning();
	
	public void set(String key, String value);
	public void set(String key, Integer value);
	public void set(String key, Float value);
	public void set(String key, boolean value);
	
	
}
