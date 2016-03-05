Introduction:
This project provides implementation of Smart Strings using 4 approaches
1. Copied Pointers
2. Owned Pointers
3. Reference Couting with COW
4. Reference Linking with COW

Open this project in Visual Studio. Under header files, you will see:
1. StringBufferCP.h
  Declaration of StringBuffer class with copied pointers.
2. StringBufferOP.h
  Declaration of StringBuffer class with Owned Pointers.
3. StringBufferRC.h
  Declaration of StringBuffer class with Reference Counting and COW.
4. StringBufferRL.h
  Declaration of StringBuffer class with Reference Linking and COW.

Similarly, in source files, you will see implementations of each of these
classes. In addition to these files, you have Test.h (under headers section), 
Test.cpp (under source section) and main.cpp (entry point for the solution).

Testing:
Test Class provides unit test coverage of all 4 approaches. It is important
to note that all StringBuffer implementations have befriended Test class so 
that it could access private methods of these classes and test them. 

Assumptions:
It is important to highlight
that reserve function is treated as a starting point for StringBuffer objects.
Reserve function must be called with a specfic size before you could append
or read from the StringBuffer object. If you append or read without calling 
reserve, an exception of type char ('e') will be thrown.
Similarly if you run out of your reserved space while appending, an exception 
will be thrown. 

To run the solution:
Open this project with Visual Studio and run the project (without debugging).
Upon running the project, main.cpp will be executed which calls test cases
over all 4 classes.

Test.cpp also demonstrates sample usage of StringBuffer API.

Profiling Results Summary:
StringBufferCP had largest memory foot print since it involves deep 
copying over each assignment.
StringBufferOP had smallest memory foot print as it just uses a 
boolean as an additional attribute. However, this approach may lead to 
dangling pointers in certain special scenarios.
StringBufferRC and StringBufferRL resulted in similar resource usage. If
application involves frequent appends (write), memory footprint becomes 
comparable to that of StringBufferCP since StringBufferRC and StringBufferRL 
perform deep copy upon write.

Author: M. Zaheer
Github Link: 

