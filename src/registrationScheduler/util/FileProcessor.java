package registrationScheduler.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import registrationScheduler.store.InterfaceConstants;
import registrationScheduler.util.Logger.DebugLevel;

/**
 * 
 * @author samara
 *
 */

public class FileProcessor {

	public  BufferedReader br1;
	public  BufferedReader br2;
	public  BufferedReader br3;
	public  String fileName1;
	public  String fileName2;
	public  String outputFile;
	private String COMPONENT = FileProcessor.class.getName();	
	
	
	/**
	 * Constructor
	 * @param inputFile1
	 * @param inputFile2
	 */
	public FileProcessor(String inputFile1_String, String inputFile2_String){
		try {
			Logger.writeMessage("Entered Constructor in  "+COMPONENT , DebugLevel.CONSTRUCTOR);
			br1= new BufferedReader(new FileReader(inputFile1_String));
			br2= new BufferedReader(new FileReader(inputFile2_String));
			br3= new BufferedReader(new FileReader(inputFile2_String));
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: "+e.getMessage());
			System.exit(1);
		}
		Logger.writeMessage("End of Constructor in  "+COMPONENT , DebugLevel.CONSTRUCTOR);
	}



	/**readFile1 reads preference input file line by line
	 * 
	 * @return String
	 * @throws IOException
	 */

	public  synchronized String readFile1() throws IOException   {
		String line1 =null;
		try {
			line1 =br1.readLine();
		} catch (IOException e) {
			System.err.println("IOException:  occured in reading Preference file"+COMPONENT+ InterfaceConstants.SPACE+e.getMessage());
			System.exit(1);
		}
		finally{

		}
		return line1;
	}	


	/**readFile2 reads DROP requests input file line by line
	 * @return String
	 * @throws IOException
	 */

	public synchronized String readFile2() throws IOException   {
		String line1=null;
		try {
			line1 =br2.readLine();
		} catch (IOException e) {
			System.err.println("IOException:  occured in reading AddDrop file"+COMPONENT+ InterfaceConstants.SPACE+e.getMessage());
			System.exit(1);
		}


		finally{

		}
		return line1;
	}

	/**readFile3 reads Add requests  from input file line by line
	 * @return String
	 * @throws IOException
	 */
	public synchronized String readFile3() throws IOException   {
		String line1=null;
		try {
			line1 =br3.readLine();
		} catch (IOException e) {
			System.err.println("IOException:  occured in reading AddDrop file"+COMPONENT+ InterfaceConstants.SPACE+e.getMessage());
			System.exit(1);
		}


		finally{

		}
		return line1;
	}

	/**
	 * 
	 * @param outputFileName
	 * @param output
	 * @throws IOException
	 */

	public  BufferedWriter writetoFile(BufferedWriter input_BufferedWriter,String output_String) throws IOException{
		try {
			input_BufferedWriter.write(output_String); 
		}catch(IOException ioException){
			System.err.println("IOException  in "+COMPONENT+InterfaceConstants.SPACE +ioException.getMessage());
			System.exit(1);
		}finally{
		}
		return input_BufferedWriter;
	}
	/**
	 * 
	 * @return String
	 */
	public String getFileName1() {
		return fileName1;
	}
	/**
	 * 
	 * @return String
	 */
	public String getFileName2() {
		return fileName2;
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
	 * @param fileName1
	 */
	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}
	/**
	 * 
	 * @param fileName2
	 */
	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}
	/**
	 * 
	 * @param outputFile
	 */
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	
	/**
	 * closeStreams closes all the readers
	 */
	public void closeStreams(){
		try {
			if(null!=br1){
				br1.close();
			}
			if(null!=br2){
				br1.close();
			}
			if(null!=br3){
				br1.close();
			}
			
		} catch (IOException e) {
			System.err.println("IOException:  occured in reading AddDrop file"+COMPONENT+ InterfaceConstants.SPACE+e.getMessage());
			System.exit(1);
		}finally{
			
		}
	}
	/**
	 * @return String
	 */
	@Override
	public String toString() {
		return "FileProcessor [fileName1=" + fileName1 + ", fileName2=" + fileName2 + ", outputFile=" + outputFile
				+ "]";
	}


}
