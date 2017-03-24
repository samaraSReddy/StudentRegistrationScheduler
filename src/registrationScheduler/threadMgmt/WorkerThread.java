package registrationScheduler.threadMgmt;

import java.io.IOException;
import java.util.Vector;
import registrationScheduler.store.IStudent;
import registrationScheduler.store.InterfaceConstants;
import registrationScheduler.store.Student;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.IScheduler;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;
import registrationScheduler.util.PrintInterface;
import registrationScheduler.util.Scheduler;
/**
 * 
 * @author samara
 *
 */
public class WorkerThread implements Runnable{



	/**
	 * Constructor
	 * @param input_fileProcessor
	 * @param input_Result
	 * @param input_StudentsHelper
	 */

	public WorkerThread(FileProcessor input_fileProcessor,PrintInterface input_Results,String input_threadName) {
		Logger.writeMessage("Entered constructor in  "+COMPONENT , DebugLevel.CONSTRUCTOR);
		this.fileProcessor=input_fileProcessor;
		results=input_Results;
		threadName=input_threadName;
		Logger.writeMessage("End of Constructor  in  "+COMPONENT , DebugLevel.CONSTRUCTOR);
	}

	private FileProcessor fileProcessor;	
	private PrintInterface results;
	private String COMPONENT = WorkerThread.class.getName();
	private String threadName;

	/**
	 *  Executes on thread start 
	 */
	@Override
	public void run() {
		Logger.writeMessage(threadName + " executing run method", DebugLevel.THREAD);
		String line;

		try {
			/**
			 *  process preference input file
			 */
			while((line=fileProcessor.readFile1())!=null ){

				IStudent student =parseStudentCourseRequestInputs(line);
				IScheduler scheduler = new Scheduler(results); 
				scheduler.registerCourses(student);
			}	

			/**
			 * process the  dropCourse request file
			 */
			while((line=fileProcessor.readFile2())!=null){
				IStudent student =parseDropInputs(line);
				IScheduler scheduler = new Scheduler(results);
				scheduler.processDropRequests(student);

			}

			/**
			 * process the  addCourse request file
			 */

			while((line=fileProcessor.readFile3())!=null ){
				IStudent student =parseAddInputs(line);
				IScheduler scheduler = new Scheduler(results);
				if( null!=student && null!= student.getAddedCourses() && !student.getAddedCourses().isEmpty()){
					scheduler.processAddRequests(student);
				}
			}

		} catch (IOException ioException) {
			System.err.println("IOException in method run of "+COMPONENT+InterfaceConstants.SPACE+ ioException.getMessage());
			System.exit(1);
		}
		catch (Exception e) {
			System.err.println("Exception in method run of "+COMPONENT+InterfaceConstants.SPACE+ e.getMessage());
			System.exit(1);
		}finally{

		}

	}


	/**
	 * parses the drop requests
	 * @param intputline_String
	 * @return IStudent
	 */
	private  IStudent parseDropInputs(String intputline_String) {
		String[] studentAddDropInputs=null;
		IStudent student =null;
		try {
			if(null!=intputline_String ){
				studentAddDropInputs = intputline_String.split(InterfaceConstants.SPACE_REGEX);
			}
			if(null!=results && null!=results.getStudentMap()){
				student= results.getStudentMap().get(studentAddDropInputs[0]);
				int val=Integer.parseInt(studentAddDropInputs[1]);

				if(val==0){
					for(int i=2;i<studentAddDropInputs.length;i++){
						if(null!= student && null!=student.getDroppedCourses() )
							student.getDroppedCourses().add(studentAddDropInputs[i]);
					}
				}

			}
		} catch (NumberFormatException e) {
			System.err.println("NumberFormatException in method parseDropInputs of "+COMPONENT+InterfaceConstants.SPACE+ e.getMessage());
			System.exit(1);
		}catch(Exception e){
			System.err.println("Exception in method parseDropInputs of "+COMPONENT+InterfaceConstants.SPACE+ e.getMessage());
			System.exit(1);
		}finally{

		}
		return student;
	}


	/**
	 * parses the add requests
	 * @param intputline_String
	 * @return IStudent
	 */
	private  IStudent parseAddInputs(String intputline_String) {
		String[] studentAddDropInputs=null;
		IStudent student =null;
		try {
			if(null!=intputline_String ){
				studentAddDropInputs = intputline_String.split(InterfaceConstants.SPACE_REGEX);
			}
			if(null!=results && null!=results.getStudentMap()){
				student= results.getStudentMap().get(studentAddDropInputs[0]);
				if(null!=student){
					Vector<String> list=student.getAddedCourses();
					int val=Integer.parseInt(studentAddDropInputs[1]);
					if(val==1){
						for(int i=2;i<studentAddDropInputs.length;i++){
							list.add(studentAddDropInputs[i]);
						}
					}else{
						student.setAddedCourses(new Vector<String>());
					}
				}	
			}
		} catch (NumberFormatException e) {
			System.err.println("NumberFormatException in method parseAddInputs of "+COMPONENT+InterfaceConstants.SPACE+ e.getMessage());
			System.exit(1);
		}catch(Exception e){
			System.err.println("Exception in method parseAddInputs of "+COMPONENT+InterfaceConstants.SPACE+ e.getMessage());
			System.exit(1);
		}finally{

		}
		return student;
	}

	/**
	 * parseStudentCourseRequestInputs parses the inputs to Student Object
	 * @param lineInputs
	 * @return IStudent
	 */

	private  IStudent parseStudentCourseRequestInputs(String lineInputs) {
		IStudent obj_Student = new Student();
		String[] studentInputs=null;


		try {
			if(null!=lineInputs ){
				studentInputs = lineInputs.split(InterfaceConstants.SPACE_REGEX);
			}
			obj_Student.setStudentName(studentInputs[0]);	
			Vector<String> list = obj_Student.getRequestedListofCourses();				
			for(int i=1;i< studentInputs.length;i++){
				list.add(studentInputs[i]);
			}
		}catch (NumberFormatException e) {
			System.err.println("NumberFormatException in method parseStudentCourseRequestInputs of "+COMPONENT+InterfaceConstants.SPACE+ e.getMessage());
			System.exit(1);
		}catch(Exception e){
			System.err.println("Exception in method parseStudentCourseRequestInputs of "+COMPONENT+InterfaceConstants.SPACE+ e.getMessage());
			System.exit(1);
		}finally{

		}

		return obj_Student;	
	}


	/**
	 * 
	 * @return FileProcessor
	 */

	public FileProcessor getFileProcessor() {
		return fileProcessor;
	}


	/**
	 * @param fileProcessor
	 */
	public void setFileProcessor(FileProcessor fileProcessor) {
		this.fileProcessor = fileProcessor;
	}


	public PrintInterface getResults() {
		return results;
	}



	public String getThreadName() {
		return threadName;
	}


	public void setResults(PrintInterface results) {
		this.results = results;
	}



	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}


	@Override
	public String toString() {
		return "WorkerThread [fileProcessor=" + fileProcessor + ", results=" + results + ", threadName=" + threadName
				+ "]";
	}


}
