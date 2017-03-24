
package registrationScheduler.util;

public class Logger{

	/**
	 * 
	 * @author samara
	 *
	 */
	public static enum DebugLevel { CONSTRUCTOR,NO_LOG,THREAD,CONTENTS,RESULTS_ENTRY 
	};

	private static DebugLevel debugLevel;
	
	/**
	 * 
	 * @param levelIn
	 */

	public static void setDebugValue (int levelIn) {
		switch (levelIn) {
		case 0:
			debugLevel = DebugLevel.NO_LOG; break;
		case 1:	
			debugLevel = DebugLevel.CONTENTS; break;
		case 2:
			debugLevel = DebugLevel.RESULTS_ENTRY; break;
		case 3:
			debugLevel = DebugLevel.THREAD; break;
		case 4: 	
			debugLevel = DebugLevel.CONSTRUCTOR; break;
		default :
			break;
		}
	}
	/**
	 * 
	 * @param levelIn
	 */
	public static void setDebugValue (DebugLevel levelIn) {
		debugLevel = levelIn;
	}
	/**
	 * 
	 * @param message
	 * @param levelIn
	 */

	public static void writeMessage (String message,DebugLevel levelIn ) {
		if (levelIn == debugLevel)
			System.out.println(message);
	}
	
	
	/**
	 * @return String
	 */ 
	public String toString() {
		return "Debug Level is " + debugLevel;
	}
}
