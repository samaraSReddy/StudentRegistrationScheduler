package registrationScheduler.objectPool;
/**
 * 
 * @author samara
 *
 */
public interface  ObjectPool {

	/**
	 * 
	 * @param course-String
	 * @return boolean
	 */
	public  boolean	borrowObject(String course);
	/**
	 * 
	 * @param course
	 * @return
	 */
	public  boolean	returnObject(String course);
	
	/**
	 * 
	 * @return int
	 */
	public int availableSeatCount();
	
	/**
	 * return nothing
	 */
	public void create();
	
	/**
	 * 
	 * @return String
	 */
	public String toString();

	
}