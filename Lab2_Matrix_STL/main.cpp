#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <vector>
#include <iostream>
#define M 2
#define N (1<<M)
 
typedef int datatype;
#define DATATYPE_FORMAT "%d"

using namespace std;
typedef vector<vector<int> > matrix;


typedef struct
{
    int ra, rb, ca, cb;
} corners; // for tracking rows and columns.
// A[ra..rb][ca..cb] .. the 4 corners of a matrix.
 
void init () 
{
    

}


// set A[a] = I
void identity(matrix &A, corners a)
{
    int i, j;
    for (i = a.ra; i < a.rb; i++)
        for (j = a.ca; j < a.cb; j++)
            A[i][j] = (datatype) (i == j);
}
 
// set A[a] = k
void set(matrix &A, corners a, datatype k)
{
    int i, j;
    for (i = a.ra; i < a.rb; i++)
        for (j = a.ca; j < a.cb; j++)
            A[i][j] = k;
}
 
// set A[a] = [random(l..h)].
void randk(matrix &A, corners a, double l, double h)
{
    int i, j;
    for (i = a.ra; i < a.rb; i++)
        for (j = a.ca; j < a.cb; j++) {
            A[i][j] = rand()%10 + 1;
        }
            
}
 
// Print A[a]
void print(matrix &A, corners a, char *name)
{
    
    int i, j;
    printf("%s = {\n", name);
    for (i = a.ra; i < a.rb; i++)
    {
        for (j = a.ca; j < a.cb; j++)
            printf(DATATYPE_FORMAT ", ", A[i][j]);
        printf("\n");
    }
    printf("}\n");
}
 
// C[c] = A[a] + B[b]
void add(matrix &A, matrix &B, matrix &C, corners a, corners b, corners c)
{
    int rd = a.rb - a.ra;
    int cd = a.cb - a.ca;
    int i, j;
    for (i = 0; i < rd; i++)
    {
        for (j = 0; j < cd; j++)
        {
            C[i + c.ra][j + c.ca] = A[i + a.ra][j + a.ca] + B[i + b.ra][j
                    + b.ca];
        }
    }
}
 
// C[c] = A[a] - B[b]
void sub(matrix &A, matrix &B, matrix &C, corners a, corners b, corners c)
{
    int rd = a.rb - a.ra;
    int cd = a.cb - a.ca;
    int i, j;
    for (i = 0; i < rd; i++)
    {
        for (j = 0; j < cd; j++)
        {
            C[i + c.ra][j + c.ca] = A[i + a.ra][j + a.ca] - B[i + b.ra][j
                    + b.ca];
        }
    }
}
 
// Return 1/4 of the matrix: top/bottom , left/right.
void find_corner(corners a, int i, int j, corners *b)
{
    int rm = a.ra + (a.rb - a.ra) / 2;
    int cm = a.ca + (a.cb - a.ca) / 2;
    *b = a;
    if (i == 0)
        b->rb = rm; // top rows
    else
        b->ra = rm; // bot rows
    if (j == 0)
        b->cb = cm; // left cols
    else
        b->ca = cm; // right cols
}

// Multiply: A[a] * B[b] => C[c], recursively.
void mul(matrix &A, matrix &B, matrix &C, corners a, corners b, corners c)
{
    corners aii[2][2], bii[2][2], cii[2][2], p;
    matrix S(N, vector<int>(N));
    matrix T(N, vector<int>(N));
    matrix P1(N, vector<int>(N));
    matrix P2(N, vector<int>(N));
    matrix P3(N, vector<int>(N));
    matrix P4(N, vector<int>(N));
    matrix P5(N, vector<int>(N));
    matrix P6(N, vector<int>(N));
    matrix P7(N, vector<int>(N));
    
    
    
    int i, j, m, n, k;
 
    // Check: A[m n] * B[n k] = C[m k]
    m = a.rb - a.ra;
    assert(m==(c.rb-c.ra));
    n = a.cb - a.ca;
    assert(n==(b.rb-b.ra));
    k = b.cb - b.ca;
    assert(k==(c.cb-c.ca));
    assert(m>0);
 
    if (n == 1)
    {
        C[c.ra][c.ca] += A[a.ra][a.ca] * B[b.ra][b.ca];
        return;
    }
 
    // Create the 12 smaller matrix indexes:
    //  A00 A01   B00 B01   C00 C01
    //  A10 A11   B10 B11   C10 C11
    for (i = 0; i < 2; i++)
    {
        for (j = 0; j < 2; j++)
        {
            find_corner(a, i, j, &aii[i][j]);
            find_corner(b, i, j, &bii[i][j]);
            find_corner(c, i, j, &cii[i][j]);
        }
    }
 
    p.ra = p.ca = 0;
    p.rb = p.cb = m / 2;
 

    set(P1, p, 0);
    set(P2, p, 0);
    set(P3, p, 0);
    set(P4, p, 0);
    set(P5, p, 0);
    set(P6, p, 0);
    set(P7, p, 0);
    
    #define ST0 set(S,p,0); set(T,p,0)
 
    // (A00 + A11) * (B00+B11) = S * T = P0
    ST0;
    add(A, A, S, aii[0][0], aii[1][1], p);
    add(B, B, T, bii[0][0], bii[1][1], p);
    mul(S, T, P1, p, p, p);
 
    // (A10 + A11) * B00 = S * B00 = P1
    ST0;
    add(A, A, S, aii[1][0], aii[1][1], p);
    mul(S, B, P2, p, bii[0][0], p);
 
    // A00 * (B01 - B11) = A00 * T = P2
    ST0;
    sub(B, B, T, bii[0][1], bii[1][1], p);
    mul(A, T, P3, aii[0][0], p, p);
 
    // A11 * (B10 - B00) = A11 * T = P3
    ST0;
    sub(B, B, T, bii[1][0], bii[0][0], p);
    mul(A, T, P4, aii[1][1], p, p);
 
    // (A00 + A01) * B11 = S * B11 = P4
    ST0;
    add(A, A, S, aii[0][0], aii[0][1], p);
    mul(S, B, P5, p, bii[1][1], p);
 
    // (A10 - A00) * (B00 + B01) = S * T = P5
    ST0;
    sub(A, A, S, aii[1][0], aii[0][0], p);
    add(B, B, T, bii[0][0], bii[0][1], p);
    mul(S, T, P6, p, p, p);
 
    // (A01 - A11) * (B10 + B11) = S * T = P6
    ST0;
    sub(A, A, S, aii[0][1], aii[1][1], p);
    add(B, B, T, bii[1][0], bii[1][1], p);
    mul(S, T, P7, p, p, p);
 
    // P0 + P3 - P4 + P6 = S - P4 + P6 = T + P6 = C00
    add(P1, P4, S, p, p, p);
    sub(S, P5, T, p, p, p);
    add(T, P6, C, p, p, cii[0][0]);
 
    // P2 + P4 = C01
    add(P3, P5, C, p, p, cii[0][1]);
 
    // P1 + P3 = C10
    add(P2, P4, C, p, p, cii[1][0]);
 
    // P0 + P2 - P1 + P5 = S - P1 + P5 = T + P5 = C11
    add(P1, P3, S, p, p, p);
    sub(S, P2, T, p, p, p);
    add(T, P6, C, p, p, cii[1][1]);
 
}
int unit_test()
{
    //mat A, B, C;
    //std::cout << A[0][0];
    
    matrix A(N, vector<int>(N));
    matrix B(N, vector<int>(N));
    matrix C(N, vector<int>(N));
    
    //return 0;
    
    corners ai = { 0, N, 0, N };
    corners bi = { 0, N, 0, N };
    corners ci = { 0, N, 0, N };
    srand(time(0));
    // identity(A,bi); identity(B,bi);
    // set(A,ai,2); set(B,bi,2);
    randk(A, ai, 0, 2);
    randk(B, bi, 0, 2);
    A[0][0] = 0;
    print(A, ai, "A");
    print(B, bi, "B");
    set(C, ci, 0);
    
    
    mul(A, B, C, ai, bi, ci);    
    print(C, ci, "C");
    return 0;
}

int main () {
    unit_test();
}

