/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab.businesslayer;

import com.lab.attendance.entity.Attendance;
import com.lab.attendance.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lab.gradebook.dao.GradeDao;
import com.lab.gradebook.entity.Grade;
import java.util.Scanner;

/**
 *
 * @author smzaheerabbas
 */
public class Application {

    private static String QUERY = "from Attendance a where classid=";

    // Retreives attendance for all classes
    public void retrieveAttendence(int i) {
        retrieveAttendence(QUERY + i);
    }

    // Retrieves attendance for specific class
    public void retrieveAttendence(String hql) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(hql);
            List resultList = q.list();
            displayResult(resultList);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    // Update Attendance for a specific student
    public void updateAttendence() {

        Scanner in = new Scanner(System.in);
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            System.out.println("Enter roll no of student");
            String rollNo = in.nextLine();// Get roll no from user

            Query q = session.createQuery("from Attendance a where a.student=" + rollNo);
            Attendance a = (Attendance) q.uniqueResult();
            a.setIsPresent(true);
            session.getTransaction().commit();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    // Displays result for attendance retrieval
    public void displayResult(List resultList) {

        for (Object o : resultList) {
            Attendance attendance = (Attendance) o;
            System.out.println("Course ID:" + attendance.getCourse().getCourseid());
            System.out.println("Fule Name: " + attendance.getStudent().getFullname());
            System.out.println("Roll No: " + attendance.getStudent().getRollno());
            System.out.println("Date: : " + attendance.getDate());
            System.out.println("Attendance: " + attendance.isIsPresent());
        }
    }

    // Retreives all grades
    public void retreiveGrade() {

        GradeDao gd = new GradeDao();
        List<Grade> result = gd.getAllGrades();
        for (Grade g : result) {
            System.out.println(g);
        }

    }

    // Updates a specific grade
    public void updateGrade() {

        Scanner in = new Scanner(System.in);
        Grade g = new Grade();
        System.out.println("Enter update name: ");
        g.setName(in.nextLine());
        System.out.println("Enter updated score: ");
        g.setScore(Integer.parseInt(in.nextLine()));
        GradeDao gd = new GradeDao();
        gd.updateGrade(g);
    }

    public void execute() {
        System.out.println("Welcome to Gradebook");
        Scanner in = new Scanner(System.in);
        int choice = -1;
        Application app = new Application();
        
        while (true) {
            
            System.out.println("Enter 1 to retrieve attendance");
            System.out.println("Enter 2 to update attendance");
            System.out.println("Enter 3 to retrieve grade");
            System.out.println("Enter 4 to update grade");
            System.out.println("Enter 5 to exit");
            choice = in.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.println("Enter class id");
                    int id =in.nextInt();
                    app.retrieveAttendence(id);
                    break;
                case 2:
                    app.updateAttendence();
                    break;
                case 3:
                    app.retreiveGrade();
                    break;
                case 4:
                    app.updateGrade();
                    break;
                default:
                    System.exit(-1);                    
            }

        }

    }

}
