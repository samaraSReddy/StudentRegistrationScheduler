Project Description

From the command line, accept the following as input, in this order:
The name of the registration preference input file (referred to as reg-preference.txt below)
The name of the add-drop input file (referred to as add-drop.txt below)
The name of the output file (referred to as output.txt below)
The number of threads to be used: referred to as NUM_THREADS below
Debug Value: an integer that controls what is printed on stdout
Validate that the correct number of command line argumets have been passed.
Validate that the value of NUM_THREADS is between 1 and 3.
Validate that the DEBUG_VALUE is between 0 and 4.
Update from Assignment-1: There are 8 courses (A, B, C, D, E, F, G, H) being offered in the summer session. The capacity for each course is 60. The total number of students is 80. Each student is required to provide preference for 5 courses. The student is asked to provide a preference for each of the courses. Top preference is specified as "6", while the lowest preference is specified as "0". A student can occur in the input file multiple times. Each occurrence is either for requesting new courses for registration, or for course(s) that (s)he wishes to drop.
Here is an example of reg-preference.txt input file.
 
Student_1 A B E F C
Student_2 C H G D A
...

Student_80 H F A C B
Here is an example of add-drop.txt input file (The digit after the student name is for add/drop: 0 indicates drop, 1 indicates add).
Student_2 0 H A
Student_2 1 B E

Student_1 0 E

Use Java, to write a program to make the assignments, so that the output.txt file looks like the following:
 
Student_1 assigned_course_name assigned_course_name assigned_course_name assigned_course_name assigned_course_name total_preference_score
Student_2 assigned_course_name assigned_course_name assigned_course_name assigned_course_name assigned_course_name total_preference_score
...
Student_80 assigned_course_name assigned_course_name assigned_course_name assigned_course_name assigned_course_name total_preference_score

Average preference_score is: X.Y

Replace X.Y in the output file with the actual average preference_score.
If a student gets her first choice, the preference score is 6. If a student gets her second choice, the preference score is 5, and so on. If a student is assigned a course she did not want, the preference score is 1. If a student is not assigned to a course, then the preference score for that course assignment is 0. The preference is based on just the initial 5 requests. The new courses that are added (based on user request or otherwise), the preference for those courses is 1.
The Driver code should create an instance of CreateWorkers and pass an instance of the FileProcessor, Results, other instances needed by the Worker to the constructor of CreateWorkers. The Driver should invoke the method startWorkers(...) in CreateWorkers and pass the NUM_THREADS as an argument.
CreateWorkers' startWorkers(...) method should create NUM_THREADS threads (via the threaded class WorkerThread), start them and join on them. The instances fo FileProcessor, Results, and classes needed for the scheduling should be passed to he constructor of the worker thread class.
The run method of the worker thread should do the following till the end of file is reached:
While the end of file has not been reached:
Invoke a method in the FileProcessor to read one line as a string
Run your algorithm to assign courses to this student.
Store the results in the data structure in the Results instance
The choice of data structure used in the Results class should be justified in the README.txt in terms of space and/or time complexity.
The Results class should implement an interface, StdoutDisplayInterface. This interface should have a method writeSchedulesToScreen(...). The driver should invoke this method on the Results instance to print the schedules, average preference score, etc. (similar format as Assignment-1) for all the students.
The Results class should implement an interface, FileDisplayInterface. This interface should have a method writeSchedulesToFile(...). The driver should invoke this method on the Results instance to print the schedules, average preference score, etc. (similar format as Assignment-1) for all the students to the output file.
Use an Object Pool instance to manage the courses (one pool for all courses, or one pool per course). If your algorithm requires you to return a spot in a course, then you should use the ObjectPool API for this purpose. You should ObjectPool API to request a spot in a course, check availability, etc.
Assume that the input file will have one unique string per line, no white spaces, and no empty lines. Also assume that the intput strings in the file do not have errors. The entries in every line are space delimited.
Except in the Logger and Singleton, do not make any other method static.
The DEBUG_VALUE, read from the command line, should be set in the Logger class. Use the DEBUG_VALUE in the following way:
DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
DEBUG_VALUE=3 [Print to stdout everytime a thread's run() method is called]
DEBUG_VALUE=2 [Print to stdout everytime an entry is added to the Results data structure]
DEBUG_VALUE=1 [Print to stdout the contents of the data structure in the store]
DEBUG_VALUE=0 [No output should be printed from the application, except the line "The average preference value is X.Y" ]
The Logger class should have a static method to writeMessage(...).
Place the FileProcessor.java in the util/ folder.



Implementation:


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
