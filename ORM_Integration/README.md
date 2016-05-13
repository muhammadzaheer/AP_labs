ORM - Hibernate

Introduction:
In this lab, we were supposed to integrate Gradebook and Attendance Projects by 
introducing a business layer of top of both projects.
Standard retrieval and update operations are provided in the business layer.

Business Layer is implemented businesslayer.Application.java class
Test cases are provided in businesslayer.Test.java class

Approach:
In order to implement business layer, underlying DAO class files of entities were exploited.
User was asked to choose operation using console menu. Appropriate method of DAO classes were called accordingly
by means of wrapper functions.

API:
Create App
Application app = new Application();

Retrieve attendence:
app.retrieveAttendence(id);

Update attendence:
app.updateAttendence();

Grade retrieval:
app.retreiveGrade();                       

Update grade:
app.updateGrade();

Launch Application:                                                           
app.execute();


Author: M. Zaheer
Github URL: https://github.com/muhammadzaheer/AP_labs/tree/master/ORM_Integration

How to run:
Import the project in your favourite IDE (e.g. Netbeans) and click on the run button to execute the test cases.
