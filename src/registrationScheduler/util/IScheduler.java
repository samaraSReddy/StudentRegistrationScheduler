package registrationScheduler.util;

import registrationScheduler.store.IStudent;
/**
 * 
 * @author samara
 *
 */
public interface IScheduler {
	
	/**
	 * 
	 * @param student
	 */
	public  void  registerCourses(IStudent student);
	
	/**
	 * 
	 * @param student
	 */
	public   void  processAddRequests(IStudent student);
	
	/**
	 * 
	 * @param student
	 */
	public   void processDropRequests(IStudent student);
	
	/**
	 * 
	 * @return PrintInterface
	 */
	public PrintInterface getResults();
	
	/**
	 * 
	 * @param results
	 */
	public void setResults(PrintInterface results);

}
