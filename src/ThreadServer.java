import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.activation.MimetypesFileTypeMap;

/**
 * 
 */

/**	ThreadServer.class takes the requests from the invoker and processes the requests in run method and sends
 * the Http Response
 * 
 * @author samara
 *
 */
public class ThreadServer extends  Thread{

	private File directory;
	private String fileName;
	private boolean fileFound;
	public static final String serverName="SamaraHttpServer";
	public static final String HTTP_HEADER="HTTP/1.1";
	public static final String SUCCESS=" 200 Ok ";
	public static final String FAILURE=" 404 Not Found ";
	public static final String DIR_NOT_FOUND ="Local directory WWW hosting the resources is not found. Please check the path ";
	public static final String LOCAL_DIR="\\WWW";
	public static final String FILE_NOT_FOUND="The file you are looking for is not found in the directory. Please check the path ";
	private final Set<ThreadNotifier> listeners= new CopyOnWriteArraySet<ThreadNotifier>();
	private boolean directoryExists;
	private Socket socket;
	
	/**This is constructor method of the ThreadServer class
	 * In this the invoking listener and the required parameters such as file ,directory are passed
	 *
	 * @param socket
	 * @param directory
	 * @param fileName
	 * @param fileFound
	 * @param directoryExists
	 * @param listener
	 */
	

	public ThreadServer(Socket socket,File directory, String fileName,boolean fileFound, boolean directoryExists,ThreadNotifier listener ) {
		this.socket = socket;
		this.directory=directory;
		this.fileName=fileName;
		this.fileFound=fileFound;
		this.directoryExists=directoryExists;
		addListener(listener);
	}
     /**In this method the processing of the http response happens as per the request using the parameters shared 
      * from the invoker class. The run method also notifies the invoker  after successful processing
      * to register the request and print the request.
      * 
      */

	@Override
	public void run() {
		try {
			String currentDate=null;
			String lastModifiedDate=null;
			MimetypesFileTypeMap mimeTypeMap= new MimetypesFileTypeMap();	

			boolean fileFound=false;
			OutputStream out = new BufferedOutputStream(socket.getOutputStream());

			if(!directory.isDirectory()){

				String header = HTTP_HEADER+ FAILURE+"\r\n"
				+"Date: " +currentDate+ "\r\n"   
				+ "Server:"+  "SamaraHttpServer"+ "\r\n"
				+"Last-Modified: " +lastModifiedDate+ "\r\n"
				+"Content-type: "+ null+"\r\n"
				+"Content-length: "+null + "\r\n";
				byte[] header2 = header.getBytes("UTF-8"); 
				out.write(header2);
				out.write((DIR_NOT_FOUND+" "+directory).getBytes());;
				out.flush();
				socket.close();
				return;
			}
			if(this.fileFound){
				File myFile = new File (directory,fileName);
				byte [] mybytearray  = new byte [(int)myFile.length()];
				FileInputStream fis = new FileInputStream(myFile);
		       	BufferedInputStream bis = new BufferedInputStream(fis);
		          bis.read(mybytearray,0,mybytearray.length);
		          Path source =Paths.get(myFile.getPath());
		          String mimeType=Files.probeContentType(source);
		          mimeType = (mimeType == null) ? "application/octet-stream":mimeType;
		          currentDate= FormatDate(null);
				lastModifiedDate= FormatDate(myFile.lastModified());
				String header = HTTP_HEADER +SUCCESS+"\r\n"
				+"Date: " +currentDate+ "\r\n"   
				+ "Server:"+  "SamaraHttpServer"+ "\r\n"
				+"Last-Modified: " +lastModifiedDate+ "\r\n"
				+"Content-type: " + mimeType  + "\r\n"
				+"Content-length: " + mybytearray.length + "\r\n\r\n";
				byte[] headerbyes = header.getBytes("UTF-8");  
				out.write(headerbyes);
				out.write(mybytearray,0,mybytearray.length);
				out.flush();
				notifyListeners();
				socket.close();
			}
			else{
				//if file is not found:Response is not displaying the content type and length and other parameters
				currentDate=FormatDate(null);
				String header = HTTP_HEADER + FAILURE +"\r\n"
				+"Date: " +currentDate+ "\r\n"   
				+ "Server:"+  "SamaraHttpServer"+ "\r\n"
				+"Last-Modified: " +lastModifiedDate+ "\r\n"
				+"Content-type: "+ null+"\r\n"
				+"Content-length: "+null + "\r\n\r\n";
		
				String htmlerror="<html>"
				+"<body>"
				+"<h1>Not Found</h1>"
				+"<p>The file you are looking for is not found in the directory"+directory.getPath() +"</p>"
				+"</body>"
				+"</html>";
				byte[] header2 = header.getBytes("UTF-8");
				out.write(header2);
				out.write(htmlerror.getBytes());
				out.flush();
				notifyListeners();
				socket.close();
				return;

			}
		}		catch (Exception e) {
				e.printStackTrace();
		}
	}
	/**In this method date is formatted as per the given standard of project0
	 * 
	 * @param inputDate
	 * @return
	 */
	public String FormatDate(Object inputDate){
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
	
	public File getDirectory() {
		return directory;
	}
	public void setDirectory(File directory) {
		this.directory = directory;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public boolean isFileFound() {
		return fileFound;
	}
	public void setFileFound(boolean fileFound) {
		this.fileFound = fileFound;
	}
	public final void addListener(final ThreadNotifier listener) {
		listeners.add(listener);
	}
	public final void removeListener(final ThreadNotifier listener) {
		listeners.remove(listener);
	}
	
	public boolean isDirectoryExists() {
		return directoryExists;
	}

	public void setDirectoryExists(boolean directoryExists) {
		this.directoryExists = directoryExists;
	}    
	/**
	 * notifies back the invoker on successful handling of http response
	 */
	private final void notifyListeners() {
		for (ThreadNotifier listener : listeners) {
			listener.notifyOfThreadComplete(this);
		}
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
	