/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import attendance.entity.Attendance;
import attendance.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import gradebook.dao.GradeDao;
import gradebook.entity.Grade;
import java.util.Scanner;

public class Test {
    public static void main (String [] args){
       
        Application app = new Application();
        //Testing retieval
        app.retrieveAttendence(5);
        
        //Testing update attendence
        app.updateAttendence();
        
        // Testing grade retrieval
        app.retreiveGrade();
        
        // Testing update grade
        app.updateGrade();
    
        // Testing execution workflow
        app.execute();
    
    
    }
}
