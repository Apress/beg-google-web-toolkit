Beginning GWT: From Novice To Professional
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

The code for the book is organized for the individual chapters as well as the sample application. Each chapter has its
own dedicated directory where you'll find the appropriate code. For example the following describes the structure of 
chapter X directory:

ChapterX/examples - a directory containing all the sample codes and examples used in the chapter.
ChapterX/gwtasks - The GWTasks version which is associated with this chapter.

As The GWTask sample application is built bit by bit in each chapter. Each chapter contains the appropriate version of 
this application.

Each GWT application (whether it is an example in the examples directory or the gwtasks application) can be built and
using a single setup script. There are two versions for the scripts for Linux/Unix and Windows platforms (The Linux/Unix
should work on OS X platform). The main task of the setup script is to run the appropriate "applicationCreator" command
and if necessary perform extram compilation which the generated GWT scripts do not do (for example, it is necessary to 
compile all server-side code in a separate process).

NOTE-1: The setup scripts will only work if the GWT_HOME and JAVA_HOME environment variables are defined.
NOTE-2: All applications where developed and tested with GWT 1.5.2.

Instructions
~~~~~~~~~~~~~

1. Make sure the GWT_HOME environment variable is defined and points to the GWT distribution on you local machine
2. Make sure the JAVA_HOME environment variable is defined and points to the JDK installation directory.
3. Go to the application directory and execute the setup script.
4. The setup script will generate two other scripts for you: *-compile and *-shell. 
5. You can run the *-compile script to compile the application and run it in a browser (that is, assuming you have no
   server side code)
6. You can execute the *-shell script to run the application in hosted mode using the GWT shell (this will work with all
   applications as the GWT shell contains an embedded tomcat server).
