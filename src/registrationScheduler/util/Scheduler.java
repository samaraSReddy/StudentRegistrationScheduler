package registrationScheduler.util;

import java.util.Vector;
import registrationScheduler.objectPool.CoursePool;
import registrationScheduler.objectPool.ObjectPool;
import registrationScheduler.store.IStudent;
import registrationScheduler.store.InterfaceConstants;
import registrationScheduler.util.PrintInterface;
import registrationScheduler.util.Logger.DebugLevel;
/**
 * 
 * @author samara
 *
 */
public class Scheduler implements IScheduler {


	private PrintInterface results ;
	private String COMPONENT= Scheduler.class.getName();


	/**
	 * Constructor
	 * @param input_results
	 */
	public Scheduler(PrintInterface input_Results ) {
		Logger.writeMessage("Entered constructor in "+COMPONENT , DebugLevel.CONSTRUCTOR);
		if(input_Results==null){
			throw new IllegalArgumentException();
		}
		results=input_Results; 
		Logger.writeMessage("End of constructor in "+COMPONENT , DebugLevel.CONSTRUCTOR);
	}


	/**
	 * Registers the student courses as per the requestedCourse from preferenceInputFile
	 * @param student
	 */
	public  synchronized void  registerCourses(IStudent student){
		try {
			if(null!=student && null!=student.getRequestedListofCourses())	{
				for(int i=0;i<student.getRequestedListofCourses().size();i++){	
					ObjectPool coursePool = (ObjectPool) CoursePool.getUniqueInstatnce(); 
					if(!student.getAllocatedCourses().contains(student.getRequestedListofCourses().get(i))&& coursePool.borrowObject(student.getRequestedListofCourses().get(i))){
						student.getAllocatedCourses().add(student.getRequestedListofCourses().get(i));
						student.setPreferenceScore(student.getPreferenceScore()+6-i);
						results.updateSeatCount(1);
					}
				}
				results.getStudentMap().put(student.getStudentName(), student);
				Logger.writeMessage("Adding entry to results Data Structure  "+student.toString(), DebugLevel.RESULTS_ENTRY);
			}
		} catch (Exception exception) {
			System.err.println("Exception occured while registering courses for student "+ student.getStudentName()+InterfaceConstants.SPACE+COMPONENT);
			System.exit(1);
		}finally{

		}
	}


	/**
	 * Adds the student courses as per the requestedCourse from addDropFile
	 * borrows the course from  ObjectPool
	 * @param student
	 */
	public  synchronized void  processAddRequests(IStudent student){

		try {
			if(student.getAddedCourses()==null||student.getAddedCourses().isEmpty() ||student.getAddedCourses().size()==0){
				return;
			}
			if(null!=student && student.getAddedCourses()!=null && !student.getAddedCourses().isEmpty() && student.getAddedCourses().size()>0){
				for(String addedCourse:student.getAddedCourses()){
					ObjectPool coursePool = (ObjectPool) CoursePool.getUniqueInstatnce(); 
					if(null!= addedCourse && !student.getAllocatedCourses().contains(addedCourse) && coursePool.borrowObject(addedCourse) ){
						student.getAllocatedCourses().add(addedCourse);
						student.setPreferenceScore(student.getPreferenceScore()+1);
						results.updateSeatCount(1);
					}
				}
				results.getStudentMap().put(student.getStudentName(), student);
				Logger.writeMessage("Adding entry to results Data Structure  "+student.toString(), DebugLevel.RESULTS_ENTRY);
				student.setAddedCourses(new Vector<String>());
			}
		} catch (Exception exception) {
			System.err.println("Exception occured while adding courses for student "+ student.getStudentName()+InterfaceConstants.SPACE+COMPONENT);
			System.exit(1);
		}finally{

		}

	}


	/**
	 * Drops the student courses as per the requestedCourse from addDropFile
	 * Returns the course to ObjectPool
	 * @param student
	 */
	public  synchronized void processDropRequests(IStudent student){

		try {
			if(null!=student && null!=student.getDroppedCourses() && student.getDroppedCourses().size()>0){
				Vector<String> newCourseList= new Vector<String>();
				for(String course :student.getAllocatedCourses()){
					if(student.getDroppedCourses().contains(course)){
						ObjectPool coursePool = (ObjectPool) CoursePool.getUniqueInstatnce(); 	
						coursePool.returnObject(course); 
						results.decrementSeatCount(1);
					}else{
						newCourseList.add(course);
					}
				}
				student.setAllocatedCourses(newCourseList);
				results.getStudentMap().put(student.getStudentName(), student);
				Logger.writeMessage("Adding entry to results Data Structure  "+student.toString(), DebugLevel.RESULTS_ENTRY);
				student.setDroppedCourses(new Vector<String>());
			}
		} catch (Exception exception) {
			System.err.println("Exception occured while processing Drop request for student "+ student.getStudentName()+InterfaceConstants.SPACE+COMPONENT);
			System.exit(1);
		}finally{

		}
	}


	/**
	 * 
	 * @return PrintInterface
	 */
	public PrintInterface getResults() {
		return results;
	}


	/**
	 * 
	 * @param results
	 */
	public void setResults(PrintInterface results) {
		this.results = results;
	}



}
