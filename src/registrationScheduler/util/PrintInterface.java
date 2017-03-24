package registrationScheduler.util;

import java.util.concurrent.ConcurrentHashMap;

import registrationScheduler.store.IStudent;

/**
 * Interface to print the results to file and Stdout
 * @author samara
 *
 */
public interface PrintInterface extends FileDisplayInterface, StdoutDisplayInterface {
	/**
	 * 
	 * @return ConcurrentHashMap<String, IStudent>
	 */
	public ConcurrentHashMap<String, IStudent> getStudentMap();
	/**
	 * 
	 * @param studentMap
	 */
	public void setStudentMap(ConcurrentHashMap<String, IStudent> studentMap);
	
	/**
	 * 
	 * @return int
	 */
	public  int getSeatCount();
	/**
	 * 
	 * @param seatCount
	 */
	public void setSeatCount(int seatCount);
	/**
	 * 
	 * @param n
	 */
	public  void updateSeatCount(int n);
	/**
	 * 
	 * @param n
	 */
	public  void decrementSeatCount(int n);
	/**
	 * 
	 * @return double
	 */
	public double calculateAveragePreferenceScore();

}
