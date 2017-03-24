package registrationScheduler.threadMgmt;


import registrationScheduler.objectPool.CoursePool;
import registrationScheduler.objectPool.ObjectPool;
import registrationScheduler.store.InterfaceConstants;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;
import registrationScheduler.util.PrintInterface;
/**
 * 
 * @author samara
 *
 */
public class CreateWorkers {

	private String COMPONENT = CreateWorkers.class.getName();
	private FileProcessor processor;
	private int threadCount;
	private PrintInterface results;

	/**
	 * Constructor
	 * @param noOfThreads_int
	 * @param input_fileProcessor
	 * @param input_Results
	 */
	public CreateWorkers(int noOfThreads_int,FileProcessor input_fileProcessor,PrintInterface input_Results ) {
			
		if(input_fileProcessor==null|| input_Results==null){
			throw new IllegalArgumentException();
		}
			Logger.writeMessage("Entered Constructor  in class "+ COMPONENT, DebugLevel.CONSTRUCTOR);
			threadCount=noOfThreads_int;	
			processor=input_fileProcessor;
			results=input_Results;
			Logger.writeMessage("End of Constructor  in class "+ COMPONENT, DebugLevel.CONSTRUCTOR);
	}

	
	/**
	 * startWorkers creates threads an joins them to
	 * process student registration with threads
	 * @throws InterruptedException
	 */
	public void startWorkers() {
		ObjectPool coursePool=(ObjectPool) CoursePool.getUniqueInstatnce(); 
		coursePool.create();
		if(threadCount>0){
			Thread[] threads= new Thread[threadCount];
				for(int i=0;i<threadCount;i++){
					WorkerThread worker=new WorkerThread(processor, results,"Thread_"+(i+1));
					threads[i]= new Thread(worker);
					threads[i].start();
				}
				for(int i=0;i<threadCount;i++){
					try{
						threads[i].join();
					}catch (InterruptedException interruptedException) {
						System.err.println("InterruptedException in method startWorkers of "+COMPONENT+InterfaceConstants.SPACE+ interruptedException.getMessage());
						System.exit(1);
				}catch (Exception e) {
					System.err.println("Exception in method startWorkers of "+COMPONENT+InterfaceConstants.SPACE+ e.getMessage());
					System.exit(1);
				}finally{

				}
			}	
		}
	}


	/**
	 * 
	 * @return int thread Count
	 */
	public int getThreadCount() {
		return threadCount;
	}

	/***
	 *  sets the threadcount
	 * @param threadCount
	 */
	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}
	
	/**
	 * 
	 * @return FileProcessor
	 */
	public FileProcessor getProcessor() {
		return processor;
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
	 * @param processor
	 */
	public void setProcessor(FileProcessor processor) {
		this.processor = processor;
	}

	/**'
	 * 
	 * @param results
	 */
	public void setResults(PrintInterface results) {
		this.results = results;
	}
	
	/**
	 * @return String
	 */
	@Override
	public String toString() {
		return "CreateWorkers [COMPONENT=" + COMPONENT + ", processor=" + processor + ", threadCount=" + threadCount
				+ ", results=" + results + "]";
	}
}
