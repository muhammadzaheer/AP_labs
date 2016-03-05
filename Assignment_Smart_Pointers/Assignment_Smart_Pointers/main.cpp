/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: smzaheerabbas
 *
 * Created on March 2, 2016, 12:37 PM
 */

#include <cstdlib>
#include <iostream>
#include <assert.h>

#include "StringBufferRL.h"
#include "Test.h"
/* To check memory leaks, uncomment the following include statement
   You need to have Visual Leak Detector installed */
//#include <vld.h>

using namespace std;

int main(int argc, char** argv) {
    
	Test test;
    test.string_buffer_CP();
    test.string_buffer_OP();
    test.string_buffer_RC();
    test.string_buffer_RL();
	getchar();
	return 0;
}

