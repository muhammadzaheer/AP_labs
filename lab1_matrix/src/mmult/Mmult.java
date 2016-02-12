/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmult;

/**
 *
 * @author smzaheerabbas
 */
import java.util.Scanner;
import java.util.Random;

public class Mmult {

    int matA[][];
    int matB[][];
    int m1, n1;
    int m2, n2;
    int result[][];
    int m3, n3;

    public void mmul() {
        m1 = 0;
        n1 = 0;
        m2 = 0;
        n2 = 0;
        m3 = 0;
        n3 = 0;
    }

    public void inputMatrices() {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter rows of matA");
        m1 = in.nextInt();
        System.out.println("Enter cols of matA");
        n1 = in.nextInt();

        System.out.println("Enter rows of matB");
        m2 = in.nextInt();
        System.out.println("Enter cols of matB");
        n2 = in.nextInt();

        matA = new int[m1][n1];
        matB = new int[m2][n2];

        System.out.println("Enter elements of matA");
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n1; j++) {
                matA[i][j] = in.nextInt();
            }
        }

        System.out.println("Enter elements of matB");
        for (int i = 0; i < m2; i++) {
            for (int j = 0; j < n2; j++) {
                matB[i][j] = in.nextInt();
            }
        }

    }

    public void generateMatrices(int m1, int n1, int m2, int n2) {
        this.m1 = m1;
        this.n1 = n1;
        this.m2 = m2;
        this.n2 = n2;

        matA = new int[m1][n1];
        matB = new int[m2][n2];

        Random r = new Random();

        System.out.println("Generating matrices");
        System.out.println("matA");
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n1; j++) {
                matA[i][j] = r.nextInt(1000);
                System.out.print(matA[i][j] + " ");
            }
            System.out.print('\n');
        }

        System.out.println("matB");
        for (int i = 0; i < m2; i++) {
            for (int j = 0; j < n2; j++) {
                matB[i][j] = r.nextInt(1000);
                System.out.print(matB[i][j] + " ");
            }
            System.out.print('\n');
        }

    }

    public void printMatrices() {

        System.out.println("MatA");
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n1; j++) {
                System.out.print(matA[i][j] + " ");
            }
            System.out.print("\n");
        }

        System.out.println("MatB");
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n1; j++) {
                System.out.print(matB[i][j] + " ");
            }
            System.out.print("\n");
        }

        System.out.println("Result");
        for (int i = 0; i < m3; i++) {
            for (int j = 0; j < n3; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.print("\n");
        }

    }

    public void iterativeMultiply() {
        if (n1 != m2) {
            System.out.println("Dimension Error: matA cannont be multiplied with matB");
        }

        result = new int[m1][n2];
        this.m3 = m1;
        this.n3 = n2;
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n2; j++) {
                int sum = 0;
                for (int k = 0; k < m2; k++) {
                    sum = sum + matA[i][k] * matB[k][j];
                }
                result[i][j] = sum;
            }

        }
    }

    public void strassen() {

        this.result = strassen(matA, matB);
        this.m3 = this.m1;
        this.n3 = this.n2;
    }

    //Assuming 'n' is divisible by 2
    public int[][] strassen(int[][] A, int[][] B) {

        int len = A.length;
        int[][] result = new int[len][len];

        if (len == 1) {
            result[0][0] = A[0][0] * B[0][0];
            return result;
        }
        int k = A.length / 2;
        int A11[][] = new int[k][k];
        int A12[][] = new int[k][k];
        int A21[][] = new int[k][k];
        int A22[][] = new int[k][k];

        int B11[][] = new int[k][k];
        int B12[][] = new int[k][k];
        int B21[][] = new int[k][k];
        int B22[][] = new int[k][k];

        copyFrom(matA, A11, 0, 0);
        copyFrom(matA, A12, 0, k);
        copyFrom(matA, A21, k, 0);
        copyFrom(matA, A22, k, k);

        copyFrom(matB, B11, 0, 0);
        copyFrom(matB, B12, 0, k);
        copyFrom(matB, B21, k, 0);
        copyFrom(matB, B22, k, k);
        /*
        int [][] P1 = strassen (A11, sub(B12, B22));
        int [][] P2 = strassen (add(A11, A12), B22);
        int [][] P3 = strassen (add(A21, A22), B11);
        int [][] P4 = strassen (A22, sub(B21, B11));
        int [][] P5 = strassen (add(A11, A22), add(B11, B22));
        int [][] P6 = strassen (sub(A12, A22), add(B21, B22));
        int [][] P7 = strassen (sub(A11, A21), add(B11, B12));
        
        int [][] C11 = add(sub(add(P5,P4), P2), P6);
        int [][] C12 = add(P1, P2);
        int [][] C21 = add(P3, P4);
        int [][] C22 = sub(sub(add(P1, P5),P3), P7); */

        int[][] P1 = strassen(add(A11, A22), add(B11, B22));
        int[][] P2 = strassen(add(A21, A22), B11);
        int[][] P3 = strassen(A11, sub(B12, B22));
        int[][] P4 = strassen(A22, sub(B21, B11));
        int[][] P5 = strassen(add(A11, A12), B22);
        int[][] P6 = strassen(sub(A21, A11), add(B11, B12));
        int[][] P7 = strassen(sub(A12, A22), add(B21, B22));

        int[][] C11 = add(sub(add(P1, P4), P5), P7);
        int[][] C12 = add(P3, P5);
        int[][] C21 = add(P2, P4);
        int[][] C22 = add(sub(add(P1, P3), P2), P6);

        copyTo(C11, result, 0, 0);
        copyTo(C12, result, 0, k);
        copyTo(C21, result, k, 0);
        copyTo(C22, result, k, k);
        //int [][] P1 = strassen (A1, )
        return result;
    }

    public void copyFrom(int[][] src, int[][] dst, int iL, int jL) {

        for (int i = 0, ik = iL; i < dst.length; i++, ik++) {
            for (int j = 0, jk = jL; j < dst.length; j++, jk++) {
                dst[i][j] = src[ik][jk];
            }
        }
    }

    public void copyTo(int[][] src, int[][] dst, int iL, int jL) {

        for (int i = 0, ik = iL; i < src.length; i++, ik++) {
            for (int j = 0, jk = jL; j < src.length; j++, jk++) {
                dst[ik][jk] = src[i][j];
            }
        }
    }

    public int[][] sub(int[][] mat1, int[][] mat2) {
        int rows = mat1.length;
        int cols = mat1[0].length;

        int sub[][] = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sub[i][j] = mat1[i][j] - mat2[i][j];
            }
        }
        return sub;
    }

    public int[][] add(int[][] mat1, int[][] mat2) {
        int rows = mat1.length;
        int cols = mat1[0].length;
        int add[][] = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                add[i][j] = mat1[i][j] + mat2[i][j];
            }
        }
        return add;
    }

    public int [][] getMatA() {
        return matA;
    }
    
    public int [][] getMatB() {
        return matB;
    }
    
    public void setMatA(int [][] matA) { this.matA = matA; }
    
    public void setMatB(int [][] matB) { this.matB = matB; }
    
    public int [][] getResult() {
        return result;
    }
    
    public void printMat(int[][] mat, int rows, int cols) {
        System.out.println("mat");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        Mmult test = new Mmult();
        test.generateMatrices(4, 4, 4, 4);
        //test.iterativeMultiply();
        //test.printMatrices();

        test.strassen();
        test.printMatrices();

        test.iterativeMultiply();
        test.printMatrices();
    }
}
