package registrationScheduler.store;

import java.util.Vector;

/**
 *Student Interface 
 * @author samara
 *
 */
public interface IStudent {

	/**
	 * 
	 * @return Vector<String>
	 */
	public String getStudentName();

	/**
	 * 
	 * @return Vector<String>
	 */
	public Vector<String> getRequestedListofCourses();

	/**
	 * 
	 * @return Vector<String>
	 */
	public Vector<String> getAllocatedCourses();

	/**
	 * 
	 * @return Vector<String>
	 */
	public int getPreferenceScore();

	/**
	 * 
	 * @return Vector<String>
	 */
	public Vector<String> getAddedCourses();

	/**
	 * 
	 * @return	Vector<String>
	 */
	public Vector<String> getDroppedCourses();

	/**
	 * 
	 * @param studentName
	 */
	public void setStudentName(String studentName);
	/**
	 * 
	 * @param requestedListofCourses
	 */
	public void setRequestedListofCourses(Vector<String> requestedListofCourses);
	/**
	 * 
	 * @param allocatedCourses
	 */
	public void setAllocatedCourses(Vector<String> allocatedCourses);
	/**
	 * 
	 * @param preferenceScore
	 */
	public void setPreferenceScore(int preferenceScore);

	/**
	 * 
	 * @param addedCourses
	 */
	public void setAddedCourses(Vector<String> addedCourses);
	/**
	 * 
	 * @param droppedCourses
	 */
	public void setDroppedCourses(Vector<String> droppedCourses);
	/**
	 * 
	 * @return String
	 */ 
	public String printResultString();	


}
