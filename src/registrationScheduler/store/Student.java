package registrationScheduler.store;

import java.util.Vector;


/** 
 * @author samara
 *
 */
public class Student implements IStudent{


	private String studentName; 
	
	private Vector<String> allocatedCourses= new Vector<String>();

	private int preferenceScore;

	private Vector<String> addedCourses= new Vector<String>();

	private Vector<String> droppedCourses= new Vector<String>();

	private Vector<String> requestedListofCourses = new Vector<String>();
	

	/**
	 * @return String
	 */
	public synchronized String getStudentName() {
		return studentName;
	}



	/**
	 * @return Vector<String>
	 */
	public synchronized Vector<String> getRequestedListofCourses() {
		return requestedListofCourses;
	}



	/**
	 * @return Vector<String>
	 */
	public synchronized Vector<String> getAllocatedCourses() {
		return allocatedCourses;
	}



	/**
	 * @return int
	 */
	public synchronized int getPreferenceScore() {
		return preferenceScore;
	}



	/**
	 * @return Vector<String>
	 */
	public synchronized Vector<String> getAddedCourses() {
		return addedCourses;
	}



	/**
	 * @return Vector<String>
	 */
	public synchronized Vector<String> getDroppedCourses() {
		return droppedCourses;
	}



	/**
	 * @param String
	 */
	public synchronized void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	/**
	 * @param Vector<String>
	 */
	public synchronized void setRequestedListofCourses(Vector<String> requestedListofCourses) {
		this.requestedListofCourses = requestedListofCourses;
	}




	/**
	 * @param Vector<String>
	 */
	public synchronized void setAllocatedCourses(Vector<String> allocatedCourses) {
		this.allocatedCourses = allocatedCourses;
	}




	/**
	 * @param preferenceScore
	 */
	public synchronized void setPreferenceScore(int preferenceScore) {
		this.preferenceScore = preferenceScore;
	}


	/**
	 * @param Vector<String>
	 */
	public synchronized void setAddedCourses(Vector<String> addedCourses) {
		this.addedCourses = addedCourses;
	}



	/**
	 * @param Vector<String>
	 */
	public synchronized void setDroppedCourses(Vector<String> droppedCourses) {
		this.droppedCourses = droppedCourses;
	}


	/**
	 * printResultString
	 */

	public synchronized String printResultString(){
		StringBuilder ouptput = new StringBuilder(this.studentName);
		ouptput.append(" ");
		for (String course : allocatedCourses) {
			ouptput.append(course + " ");
		}
		ouptput.append(this.preferenceScore);
		return ouptput.toString();		
	}



	/**
	 * @return String 
	 */
	@Override
	public synchronized String toString() {
		return "Student [studentName=" + studentName + ", allocatedCourses=" + allocatedCourses + ", preferenceScore="
				+ preferenceScore + ", addedCourses=" + addedCourses + ", droppedCourses=" + droppedCourses
				+ ", requestedListofCourses=" + requestedListofCourses + "]";
	}



	/**
	 *  @return int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addedCourses == null) ? 0 : addedCourses.hashCode());
		result = prime * result + ((allocatedCourses == null) ? 0 : allocatedCourses.hashCode());
		result = prime * result + ((droppedCourses == null) ? 0 : droppedCourses.hashCode());
		result = prime * result + preferenceScore;
		result = prime * result + ((requestedListofCourses == null) ? 0 : requestedListofCourses.hashCode());
		result = prime * result + ((studentName == null) ? 0 : studentName.hashCode());
		return result;
	}


	
	/**
	 * @return boolean 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (addedCourses == null) {
			if (other.addedCourses != null)
				return false;
		} else if (!addedCourses.equals(other.addedCourses))
			return false;
		if (allocatedCourses == null) {
			if (other.allocatedCourses != null)
				return false;
		} else if (!allocatedCourses.equals(other.allocatedCourses))
			return false;
		if (droppedCourses == null) {
			if (other.droppedCourses != null)
				return false;
		} else if (!droppedCourses.equals(other.droppedCourses))
			return false;
		if (preferenceScore != other.preferenceScore)
			return false;
		if (requestedListofCourses == null) {
			if (other.requestedListofCourses != null)
				return false;
		} else if (!requestedListofCourses.equals(other.requestedListofCourses))
			return false;
		if (studentName == null) {
			if (other.studentName != null)
				return false;
		} else if (!studentName.equals(other.studentName))
			return false;
		return true;
	}

}
