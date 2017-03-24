
package registrationScheduler.driver;
import java.io.File;

import registrationScheduler.store.InterfaceConstants;
import registrationScheduler.store.Results;
import registrationScheduler.threadMgmt.CreateWorkers;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;
import registrationScheduler.util.PrintInterface;

/**
 * 
 * @author samara
 *
 */

public class Driver{

	private static String COMPONENT = Driver.class.getName();




	/**
	 * 
	 * @param args
	 * 
	 * This is the main method for the Driver class 
	 * 1. reads input arguments  from the user and validates.  
	 *  checks for arg0 -preference-Input.
	 *  checks for arg1 -registrationTime-input.
	 *  checks for arg2 -output-File.
	 *  checks for arg3 -DebugLevel.
	 *  2. process the registration for Students and prints  the output in output file.
	 *  
	 */

	public static void main(String args[]) {

		try{
			validateInputArguments(args);
			int debug= Integer.parseInt(args[4]);
			int noOfThreads=Integer.parseInt(args[3]);
			Logger.setDebugValue(debug);
			FileProcessor 	fileProcessor= new FileProcessor(args[0],args[1]);
			PrintInterface displayInterface= new Results(fileProcessor,args[2]);
			CreateWorkers createWorkers= new CreateWorkers(noOfThreads,fileProcessor,displayInterface);
			createWorkers.startWorkers();
			displayInterface.writeScheduleToFile();
			displayInterface.writeScheduleToScreen();
			fileProcessor.closeStreams();
			Logger.writeMessage("Average preference_score is: "+displayInterface.calculateAveragePreferenceScore(), DebugLevel.NO_LOG);

		}catch (NumberFormatException numberFormatException) {
			System.err.println("NumberFormatException in method main of "+COMPONENT+InterfaceConstants.SPACE+ numberFormatException.getMessage());
			System.exit(1);
		}catch (Exception exception) {
			System.err.println("Exception in in method main of "+COMPONENT+InterfaceConstants.SPACE+ exception.getMessage());
			System.exit(1);
		}
		finally{
		}
	} 


	/**
	 * validates given arguments
	 * @param args
	 */
	public static void validateInputArguments(String[] args){
		String inputFile1=null;
		String inputFile2=null;
		String outputFileName=null;
		int threadCount=0;
		int debugLevel=-1;
		try {
			if (null==args || args.length!=5) {
				System.out.println(args.length);
				System.err.println("No of arguments should not be more or less than 5" );
				System.exit(1);
			}
			if (null !=args[0] && !args[0].equals("${arg0}")) {
				inputFile1 = args[0];
				validateInputFile(inputFile1);
			} else {
				System.err.println("parameter arg0 preference-input fileName not set." );
			}
			if(null !=args[1] &&  !args[1].equals("${arg1}") ){
				inputFile2 = args[1];
				validateInputFile(inputFile2);
			} else {
				System.err.println("parameter arg1 reg-input fileName not set." );
			}
			if (null !=args[2] && !args[2].equals("${arg2}")) {
				outputFileName = args[2];
				File file= new File(outputFileName);

				if(!file.exists() ){
					System.err.println("The file "+outputFileName+ " doesnot exist"  );
					System.exit(1);
				}
			} else {
				System.err.println("parameter arg2 output fileName not set." );
			}
			if (null !=args[3] &&  !args[3].equals("${arg3}")) {
				threadCount=Integer.parseInt(args[3]);
				if(1>threadCount || threadCount>3){
					System.err.println("parameter arg3 threadCount should be between 1 and 3 inclusive." );
					System.exit(1);

				}
			} else {
				System.err.println("parameter arg3 threadCount not set." );
				System.exit(1);
			}
			if (null !=args[4] &&  !args[4].equals("${arg4}")) {
				debugLevel=Integer.parseInt(args[4]);
				if(0>debugLevel || debugLevel>4){
					System.err.println("parameter arg4 debugLevel should be between 0 and 4 inclusive." );
					System.exit(1);
				}
			} else {
				System.err.println("parameter arg4 debugLevel not set." );
				System.exit(1);
			}
		} catch (NumberFormatException e) {
			System.err.println("NumberFormatException occured in method validateInputArguments "+COMPONENT + InterfaceConstants.SPACE +e.getMessage());
			System.exit(1);
		}
		catch (Exception exception) {
			System.err.println("Exception in in method validateInputArguments of "+COMPONENT+InterfaceConstants.SPACE+ exception.getMessage());
			System.exit(1);
		}
		finally{
		}
	}

	/**
	 * validate if the the input file exists and readable
	 * @param input_FileName
	 */
	public static void validateInputFile(String input_FileName){
		try {
			File file= new File(input_FileName);
			if(!file.exists() || !file.canRead()){
				System.err.println("The file "+input_FileName+ " doesnot exist or is not readable"  );
				System.exit(1);
			}
		}catch (Exception exception) {
			System.err.println("Exception in in method validateInputArguments of "+COMPONENT+InterfaceConstants.SPACE+ exception.getMessage());
			System.exit(1);
		}
		finally{
		}
	}
} 

