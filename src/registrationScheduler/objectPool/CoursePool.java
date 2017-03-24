package registrationScheduler.objectPool;

import java.util.concurrent.ConcurrentHashMap;

import registrationScheduler.store.InterfaceConstants;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;

/** 
 * @author samara
 *
 */
public class CoursePool implements ObjectPool{


	private ConcurrentHashMap<String, Integer> courseMap= new ConcurrentHashMap<String, Integer>();
	private String COMPONENT= CoursePool.class.getName();
	private volatile static CoursePool instance;
	private volatile int seatCount;

	private  CoursePool() {
		Logger.writeMessage("Constructor called in class "+COMPONENT, DebugLevel.CONSTRUCTOR);
	}

	/**
	 * Gives uniqueInstance -singleton
	 * @return registrationScheduler.objectPool.CoursePool
	 */
	public static CoursePool getUniqueInstatnce(){
		if(instance==null){
			synchronized(CoursePool.class){
				if(instance==null)
					instance = new CoursePool();
			}
		}

		return instance;
	}

	/**
	 * Creates CoursePool with courses with fixed size-60
	 */
	@Override
	public void create() {
		if(null!=courseMap && courseMap.size()==0){
			courseMap.put(InterfaceConstants.COURSE_1, 60);
			courseMap.put(InterfaceConstants.COURSE_2, 60);
			courseMap.put(InterfaceConstants.COURSE_3, 60);
			courseMap.put(InterfaceConstants.COURSE_4, 60);
			courseMap.put(InterfaceConstants.COURSE_5, 60);
			courseMap.put(InterfaceConstants.COURSE_6, 60);
			courseMap.put(InterfaceConstants.COURSE_7, 60);
			courseMap.put(InterfaceConstants.COURSE_8, 60);
			seatCount=480;
		}
	}
	/**
	 * @return boolean 
	 */
	@Override
	public synchronized boolean borrowObject(String course) {
		if(null!=course && null!=courseMap && courseMap.size()>0 && courseMap.containsKey(course) && courseMap.get(course)>0){
			seatCount--;
			courseMap.put(course, courseMap.get(course)-1);
			return true;
		}
		return false;

	}

	/**
	 * @return boolean 
	 */
	@Override
	public synchronized boolean returnObject(String course) {
		if(null!=courseMap){
			courseMap.put(course, courseMap.get(course)+1);
			seatCount++;
			return true;
		}
		return false;
	}

	/**
	 * @return int
	 */
	public synchronized int availableSeatCount(){
		return seatCount;
	}
	/**
	 * @return boolean 
	 */
	@Override
	public String toString() {
		return "CoursePool [courseMap=" + seatCount+InterfaceConstants.SPACE+courseMap.toString() + "]";
	}


}
