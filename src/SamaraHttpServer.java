
import java.io.File;	
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class SamaraHttpServer  {
	
	private static ThreadListener listener;
	private static  Map<String,Integer> resourceMap;
	/**Static  Main method 
	 * Hosts the server and displays the PortNo and Hostname on console 
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		ServerSocket serverSocket = new ServerSocket(0);	     
		System.out.println("listening on Host: "+Inet4Address.getLocalHost().getHostAddress());
		System.out.println("listening on port: " + serverSocket.getLocalPort());
		handleRequets(serverSocket);	
	
	}

	public static void handleRequets(ServerSocket serverSocket) {

		try{	
			while (true){
			Socket socket = serverSocket.accept();	
			Scanner scanner  = new Scanner(socket.getInputStream());
			String method;
			String uri=null;		
			if(scanner.hasNext()){
			method = scanner.next();
			}
			if(scanner.hasNext()){
			 uri= scanner.next();
			}
			if(uri!=null){
			File path = new File(uri.substring(1).split("[?]")[0]);
			String fileName=path.toString();
			File base = path.getAbsoluteFile();
			File parentPath =new File(base.getParent());
			File directory= new File(parentPath, "WWW");
			if(directory.exists()){	
				boolean fileFound=false;
				String[] fileList = directory.list();
				if(null !=fileList && fileList.length>0){
					for (String file : fileList)
					{
						if(file.equals(fileName)){	
							fileFound=true;
							break;
						}
					}
				} 
				if(listener==null &&resourceMap==null){
					listener= new ThreadListener(resourceMap);
				}
				Thread thread= new Thread(new ThreadServer(socket,directory,fileName,fileFound,true, listener));
				thread.start();
			}
			else{
				ThreadListener listener = new ThreadListener(resourceMap);
				new Thread(new ThreadServer(socket,directory,fileName,false,false,listener)).start();
				}
			 }	
			}
			}catch (Exception e) {
				e.printStackTrace();
		}
	 }
	}

