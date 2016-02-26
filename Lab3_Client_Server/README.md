Author: M. Zaheer

Approach:
A simple client server program allowing client to persist note against a user at server-side and retrieve the note for a given user.

Client creates a Note and sends it to Server.
Note Object contains operation, username and text.
Server maintains an ArrayList of Note.

If operation is 0, Server persists the Note into the ArrayList
else if operation is 1, Server searches the ArrayList and returns Note objects
  which have username equals to Note.username. 

Execute:
To run this app, import the project into Netbeans.
Run Server.java
and then run Client.java.

Test Case:
lab3.TestApp contains an echo test case. A simple note is presisted and then 
retrieved. To run this test, first execute Server.java and then execute 
TestApp.java.

