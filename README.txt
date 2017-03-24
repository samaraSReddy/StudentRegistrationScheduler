Name : Samara Simha Reddy Yerramada
BNO: B00670217


Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean	

The above coomand will clean the classes and build directory. 

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

The above command will compile the classes 

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## To run code
ant -buildfile src/build.xml run -Darg0=preference-input.txt -Darg1=addDrop-input.txt -Darg2=output.txt  -Darg3=2 -Darg4=2

preference-input.txt--> InputFile
regTime-input.txt --> RegistrationInputFile
output.txt--> OutPutfile
arg3 is the debugLevel

All three files should be present in the directories specified.



-----------------------------------------------------------------------

## To create tarball for submission

tar -cvf SamaraSimhaReddy_Yerramada_assign_2.tar SamaraSimhaReddy_Yerramada_assign_1/
gzip SamaraSimhaReddy_Yerramada_assign_2.tar	

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.â€�

Samara Simha Reddy Yerramada

[Date: ] -- 02/12/2017 4:45 PM 

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)

className: FileProcessor.java

1.operation : readInputFromFile
Metod:readFile1
Complexity: O(1) 
DataStructure Used String>-- O(1) spaceComplexity.


2.operation : WriteInputToFile
Metod:writetoFile
Complexity: O(1) --> Writes one line at a time to file


Class Name: IStudent.java
 Interface for Student class with method access 

ClassName: Student.java
 
attributes with getters and setters namely
String studentName; 
Vector<String> requestedListofCourses-->O(N) for search 
Vector<String> allocatedCourses-->O(N) for search 
int preferenceScore;



ClassName: Scheduler.java:
 
 Algorithm Used : 
 
 1.Process each line  from PreferenceInput File and allocate from course pool  to student calculate preference score and store in Results D/S //Method: registerCourses
 2.Process each line  from DropInput file and return  course pool and remove from allocated courses of student.//Method: processDropRequests
 3.Process each line  from AddInput file and borrow from course pool//Method: processAddRequests
 4. Calculate  AveragePreferenceScore
 
 Object pool is implemented  on courses and No of seats available 
 
 className: ObjectPool.java
 
 Interface to CoursePoll
 
 className: CoursePool.java
 
methods:
	boolean	borrowObject(String course);--> returns true if requested course is available
	
	 boolean returnObject(String course);returns true if requested course is returned
	
	int availableSeatCount(); returns count of available seats

	void create();--> Create Course  map with seats 
	
	String toString();--> returns the content of map as string


ClassName: CreateWorkers.java
method:
startWorkers--> Creates the fixed no of  worker threads and joins them 

ClassName: WorkerThread.java
method:
run--> executes the thread by reading the inputs and scheduling the courses and storing to results class


ClassName: Results.java

Intialized with FileProcessor to print the output to file. 

ConcurentHashMap<String,IStudent> studentMap--> Output Results are stored into Map.O(N) Time Complexity

justification for results DataStructure : ConcurentHashMap is used as it is multiThreaded Environment and as we have Add drop requests apart from course requests
Used map as it is easy to access student results with StudentName Key.


Method:writeScheduleToFile

Time Complexity: O(N)--> to print output of N-students into a file.


Method:writeScheduleScreen

Time Complexity: O(N)--> to print output of N-students into a Screen.

Method:calculateAveragePreferenceScore

Time Complexity: O(N)--> to :calculate Average Preference Score.

-----------------------------------------------------------------------

Provide list of citations (urls, etc.) from where you have taken code
(if any).

Not Applicable.
