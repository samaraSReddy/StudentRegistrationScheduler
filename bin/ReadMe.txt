1.The server can be started by running the executable file SamaraHttpServer.run as  ./SamaraHttpServer.run

2. This will print out the Hostname and Port on which the server is hosted as

Example :  
listening on Host: 128.226.180.169
listening on port: 49091


3. Brief description on implementation.

a.The implemntation of the MultiThreadedHttpServer is done in java using socket programming with inbuilt java api's available in "java.net.*" package 

b.There are four major source files SamaraHttpServer.Java, ThreadServer.java, ThreadListener.java, ThreadNotifier.java 

c.SamaraHttpServer is the class with main method responsible for starting server on host on available port and posts that on console

d.SamaraHttpServer.java also contains the static object of class ThreadListener to register and print the file requested count for different hosts.

e. When a client hits the Url the main method raises a new thread and passes the request to process to ThreadServer.java with the inputs
like source name,requested the directory details,socket details and static object of ThreadListener.

f. ThreadServer process the requests in run method for different cases and outputs the response to client witha  Http header and response.

g.ThreadListener implenets the interface ThreadNotifer 

h.In different cases  ThreadServer.run  notifies the ThreadListener.java via notifyThreadListener method.

i.The ThreadLstener on notification if source exists and operation is succesful it registers the request in a map called "resourcemap " 
  containing the filename and count and prints it. 
  
j.After notifying the listener the socket is closed.  

h.MANIFEST.MF contains the main class information to run on server start.

i. "runner.sh" is the shell script code used to convert the java -jar to runnable file.

i. makefile contains all the commands to build the jar and used the runner.sh and MANIFEST.MF to build the runnable "SamaraHttpServer.run".



4. Testing with sample Inputs

The server can be reached at url http://128.226.180.169:49091/source.xxx  where source.xxx is one of the resources in the WWWW/ directory
suppose source.xxx is pdf-sample.pdf-sample then the request url is

http://128.226.180.169:49091/pdf-sample.pdf

This url can be accessed from any other remote machine. Create an empty directory with command mkdir TestingSamaraHttpServer 
and use wget to acess the url. lets look at different test cases


Case1 : Directory exists and requested file exists 
	
wget -S  http://128.226.180.169:49091/pdf-sample.pdf
Sample response on remote client machine as follows

Connecting to remote07.cs.binghamton.edu (remote07.cs.binghamton.edu)|128.226.180.169|:56969... connected.
HTTP request sent, awaiting response...
  HTTP/1.1 200 Ok
  Date: Thu, 08 Sep 2016 22:55:43 GMT
  Server:SamaraHttpServer 1.0
  Last-Modified: Fri, 02 Sep 2016 15:56:53 GMT
  Content-type: application/pdf
  Content-length: 7945
Length: 7945 (7.8K) [application/pdf]
Saving to: `pdf-sample.pdf'

100%[======================================>] 7,945       --.-K/s   in 0s


Since pdf-sample.pdf is requested once Server prints the log as follows:
pdf-sample.pdf|128.226.180.164|1



Case2 : Directory exists and requiested file doesnt exists

Requested source samara.pdf doesnt exists in the directory WWW/
wget -S  http://128.226.180.169:49091/samara.pdf
Sample Response on client :

Connecting to remote07.cs.binghamton.edu (remote07.cs.binghamton.edu)|128.226.180.169|:56969... connected.
HTTP request sent, awaiting response...
  HTTP/1.1 404 Not Found
  Date: Thu, 08 Sep 2016 22:57:56 GMT
  Server:SamaraHttpServer 1.0
  Last-Modified: null
  Content-type: null
  Content-length: null
2016-09-08 18:57:56 ERROR 404: Not Found.

There wont be any log on the count increment of file on server as the requested file is not found.

Case3: Directory doesn't Exists 

a.Stop the server with CNTL+C on host machine Rename the WWW/ directory using command mv WWWW WWWW1 before starting the server with executable

b. Try hitting the server from different remote using putty and wget as follows
wget -S  http://128.226.180.169:49091/pdf-sample.pdf
Sample response on the client machine:

Connecting to remote07.cs.binghamton.edu (remote07.cs.binghamton.edu)|128.226.180.169|:44148... connected.
HTTP request sent, awaiting response...
  HTTP/1.1 404 Not Found
  Server:SamaraHttpServer 1.0
  Content-length: 0
  Content-type:
  Local directory WWW hosting the resources is not found. Please check the path  /import/linux/home/XXXXX/syerram1-project0/WWW
2016-09-08 19:04:23 ERROR 404: Not Found.




Note : 1.WWW should be in the same directory as the executable file
       2.when hitting using wget -S it also prints the entire header information.
      

