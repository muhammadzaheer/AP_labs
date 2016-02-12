/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmult;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author smzaheerabbas
 */
public class MmultTest {
    
    public MmultTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of generateMatrices method, of class Mmult.
     */
    @Test
    public void testGenerateMatrices() {
        System.out.println("generateMatrices");
        int m1 = 2;
        int n1 = 3;
        int m2 = 7;
        int n2 = 5;
        Mmult instance = new Mmult();
        instance.generateMatrices(m1, n1, m2, n2);
        assertEquals(instance.getMatA().length, m1);
        assertEquals(instance.getMatA()[0].length, n1);
        assertEquals(instance.getMatB().length, m2);
        assertEquals(instance.getMatB()[0].length, n2);
    }

    @Test
    public void testIterativeMultiply() {
        System.out.println("iterativeMultiply");
        Mmult instance = new Mmult();
        
        int [][] matA = new int [2][2];
        int [][] matB = new int [2][2];
        
        matA[0][0] = 1;
        matA[0][1] = 2;
        matA[1][0] = 3;
        matA[1][1] = 4;
        
        matB[0][0] = 5;
        matB[0][1] = 6;
        matB[1][0] = 7;
        matB[1][1] = 8;
        
        instance.setMatA(matA);
        instance.setMatB(matB);
        
        instance.m1 = 2;
        instance.n1 = 2;
        instance.m2 = 2;
        instance.n2 = 2;
        
        instance.iterativeMultiply();
        
        int[][]result = instance.getResult();
        
        assertEquals(19, result[0][0]);
        assertEquals(22, result[0][1]);
        assertEquals(43, result[1][0]);
        assertEquals(50, result[1][1]);
    }

    /**
     * Test of strassen method, of class Mmult.
     */
    @Test
    public void testStrassen() {
        System.out.println("iterativeMultiply");
        Mmult instance = new Mmult();
        
        int [][] matA = new int [2][2];
        int [][] matB = new int [2][2];
        
        matA[0][0] = 1;
        matA[0][1] = 2;
        matA[1][0] = 3;
        matA[1][1] = 4;
        
        matB[0][0] = 5;
        matB[0][1] = 6;
        matB[1][0] = 7;
        matB[1][1] = 8;
        
        instance.setMatA(matA);
        instance.setMatB(matB);
        
        instance.m1 = 2;
        instance.n1 = 2;
        instance.m2 = 2;
        instance.n2 = 2;
        
        instance.strassen();
        
        int[][]result = instance.getResult();
        
        assertEquals(19, result[0][0]);
        assertEquals(22, result[0][1]);
        assertEquals(43, result[1][0]);
        assertEquals(50, result[1][1]);
    }

}
