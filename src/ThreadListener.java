import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;


public class ThreadListener implements ThreadNotifier {

	Map<String,Integer>  resourceMap;

	public ThreadListener(Map<String, Integer> resourceMap) {
		this.resourceMap=resourceMap;
	}

	private void printRequestMap(String requestedHost,String fileName,Map<String,Integer> resourceMap) {
		if(resourceMap!=null&& resourceMap.size()>0)
		{	
			if(resourceMap.get(fileName)!=null ){
				System.out.println((fileName+"|"+requestedHost+"|"+resourceMap.get(fileName)));
			}
		}
	}			


	private  void registerRequest(Map<String,Integer> resourceMap,Socket socket,File directory, String fileName) throws IOException {
		if(resourceMap!=null && resourceMap.get(fileName)!=null){
			Integer count=resourceMap.get(fileName);
			if(count !=null &&count>0){
				count++;
			}
			else{
				count=1;
			}
			resourceMap.put(fileName, count);
		}else{
			if(resourceMap==null){
				resourceMap= new  HashMap<String, Integer>();
				resourceMap.put(fileName, 1);
			}else{
				resourceMap.put(fileName, 1);
			}
		}
		this.setResourceMap(resourceMap);
	}

	public static String getDate(Object inputDate){
		String date=null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		if(inputDate==null){
			date= dateFormat.format(calendar.getTime());
		}
		else{
			date=dateFormat.format(inputDate);
		}
		return date;
	}

	@Override
	public void notifyOfThreadComplete(Thread thread) {
		try{
			ThreadServer server=(ThreadServer)thread;
			if(server.isFileFound()){
				registerRequest(resourceMap,server.getSocket(), server.getDirectory(), server.getFileName());		
			
			printRequestMap(server.getSocket().getInetAddress().getHostAddress(),server.getFileName(), this.getResourceMap());
			}
		}
		catch (Exception e) {
			System.out.println("Error inside ");
		}
	}
	public Map<String, Integer> getResourceMap() {
		return resourceMap;
	}
	public void setResourceMap(Map<String, Integer> resourceMap) {
		this.resourceMap = resourceMap;
	}	


}
