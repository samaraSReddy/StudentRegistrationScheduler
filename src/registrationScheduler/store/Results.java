
package registrationScheduler.store;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;
import registrationScheduler.util.PrintInterface;


/**
 * 
 * @author samara
 *
 */
public class Results implements PrintInterface	 {

	private String outputFile;
	private FileProcessor fileProcessor;
	private ConcurrentHashMap<String,IStudent> studentMap= new  ConcurrentHashMap<String,IStudent>();
	private  Logger logger= new Logger();	
	private static String COMPONENT = Results.class.getName();
	private int averagePreferenceScore;
	private volatile int seatCount;
	
	
	/**	
	 * Constructor 
	 * @param input_FileProcessor
	 * @param input_OutPutFile
	 */
	public Results(FileProcessor input_FileProcessor,String input_OutPutFile){
		Logger.writeMessage("Entered  Constructor in "+COMPONENT , DebugLevel.CONSTRUCTOR);
		try {
			fileProcessor=input_FileProcessor;
			outputFile=input_OutPutFile;

		} catch (Exception e) {
			System.err.println("Error initializing Results");
			System.exit(1);
		}finally{
			
		}
		Logger.writeMessage("End of  Constructor in "+COMPONENT , DebugLevel.CONSTRUCTOR);
	}

	/**
	 * This method writeScheduleToFile prints output to file	
	 */
	@Override
	public void writeScheduleToFile() {
		try {
			Map<String,IStudent> map=new TreeMap<String,IStudent>(new StudentSortComparator());
			map.putAll(getStudentMap());
			File outputfile = new File(outputFile);
			FileWriter fw = new FileWriter(outputfile);
			BufferedWriter bw = new BufferedWriter(fw);
			String  output ;
			for(Map.Entry<String, IStudent> entry : map.entrySet()){
				output = new String("");	
				IStudent obj_Student= entry.getValue();
				output=output+obj_Student.printResultString()+"\n";
				fileProcessor.writetoFile(bw,output);
			}

			double avgPrefScore=calculateAveragePreferenceScore();
			output="Average preference_score is: "+avgPrefScore+"\n";		
			bw=fileProcessor.writetoFile(bw,output);	
			bw.close();
		}
		catch (Exception e) {
			System.err.println("Exception: Error while printing the outputfile in writeScheduleToFile "+COMPONENT+InterfaceConstants.SPACE+e.toString());
			System.exit(1);
		}finally{
		} 

	}

	/**
	 * This method writeScheduleToScreen writes the schedule to screen
	 * 
	 */
	public void writeScheduleToScreen() {
		try {
			Map<String,IStudent> map=new TreeMap<String,IStudent>(new StudentSortComparator());
			map.putAll(getStudentMap());
			String  output =null;

			for(Map.Entry<String, IStudent> entry : map.entrySet()){
				output = new String("");	
				IStudent obj_Student= entry.getValue();
				output=output+obj_Student.printResultString();
				System.out.println(output);
			}

			double avgPrefScore=calculateAveragePreferenceScore();
			output="Average preference_score is: "+avgPrefScore;		
			System.out.println(output);
		}
		catch (Exception e) {
			System.err.println("Exception: Error while printing the output to screen in writeScheduleToScreen "+COMPONENT+InterfaceConstants.SPACE +e.toString());
			System.exit(1);
		}finally{
		} 
	}
	
	/**
	 * @return double- AveragePreferenceScore
	 * calculates AveragePreferenceScore of Students
	 */
	public double calculateAveragePreferenceScore(){
		int SumofPreferenceScore=0;
		double avgPreferenceScore=0;
		try {
			for(Map.Entry<String, IStudent> entry : studentMap.entrySet()){
				IStudent obj_Student= entry.getValue();
				SumofPreferenceScore+=obj_Student.getPreferenceScore();
			}
			avgPreferenceScore=(double)(SumofPreferenceScore/studentMap.size());
		} catch (Exception e) {
			System.err.println("Exception: Error while calucating AveragePreferenceScore in calculateAveragePreferenceScore "+COMPONENT+InterfaceConstants.SPACE +e.toString());
			System.exit(1);
		}finally{
			
		}
		
		return avgPreferenceScore;
	}
	

	/**
	 * 
	 * @return Logger
	 */
	public Logger getLogger() {
		return logger;
	}
	/**
	 * 
	 * @param logger
	 */
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	/**
	 * 
	 * @return FileProcessor
	 */
	public FileProcessor getFileProcessor() {
		return fileProcessor;
	}
	
	/**
	 * 
	 * @param fileProcessor
	 */
	public void setFileProcessor(FileProcessor fileProcessor) {
		this.fileProcessor = fileProcessor;
	}


	/**
	 * 
	 * @return String
	 */
	public String getOutputFile() {
		return outputFile;
	}


	/**
	 * 
	 * @param outputFile
	 */
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	/**
	 * 
	 * @return ConcurrentHashMap
	 */
	public synchronized ConcurrentHashMap<String, IStudent> getStudentMap() {
		return studentMap;
	}
	/**
	 * 
	 * @param studentMap
	 */
	public synchronized void setStudentMap(ConcurrentHashMap<String, IStudent> studentMap) {
		this.studentMap = studentMap;
	}
	/**
	 * 
	 * @return int	- averagePreferenceScore
	 */
	public int getAveragePreferenceScore() {
		return averagePreferenceScore;
	}
	/**
	 * 
	 * @param averagePreferenceScore
	 */
	public void setAveragePreferenceScore(int averagePreferenceScore) {
		this.averagePreferenceScore = averagePreferenceScore;
	}
	
	/**
	 * gives the seats allocated count
	 * @return int 
	 */
	public synchronized int getSeatCount() {
		return seatCount;
	}

	/**
	 * setSeatCount if incremented
	 * @param seatCount
	 */
	public synchronized void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
	
	/**
	 * updates SeatCount allocated
	 * @param n
	 */
	public synchronized void updateSeatCount(int n) {
		this.seatCount = seatCount+n;
	}
	
	/**
	 * @param n
	 */
	public synchronized void decrementSeatCount(int n) {
		this.seatCount = seatCount-n;
	}
	
	
	/**
	 * @return String
	 */
	@Override
	public String toString() {
		return "Results [outputFile=" + outputFile + ", fileProcessor=" + fileProcessor + ", studentMap=" + studentMap
				+ ", logger=" + logger + ", averagePreferenceScore=" + averagePreferenceScore + "]";
	}


} 


