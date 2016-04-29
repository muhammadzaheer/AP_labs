A simple interpreter written in Java which reads a file with a declarative
language containing variable declaration and simple arithmetic operations. 
These commands are then executed and the result is displayed. 

Only Integer datatype is supported.

Approach:
File is read and each line (code) is stored in a link list
For each code, Operation is determined and performed.
Variables encountered during execution are stored in a HashMap <String, Integer>
for fast retrieval and update.


How to run:
To execute the project, clone the repository, import it as a Netbeans Project
in Netbeans IDE and execute interpreter.Interpreter.java.

To try out the project, edit test.txt, execute Interpreter.java 
and see what happens.

Testing:
Class Test.java contains 3 test-cases executed from files test.txt, test2.txt
and test3.txt. See Test.java for details.

Author: Muhammad Zaheer
Github URL: https://github.com/muhammadzaheer/AP_labs/tree/master/Lab9_Interpreter 
