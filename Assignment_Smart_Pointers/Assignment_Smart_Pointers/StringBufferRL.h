/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   StringBufferRL.h
 * Author: smzaheerabbas
 *
 * Created on March 4, 2016, 5:44 PM
 */

#ifndef STRINGBUFFERRL_H
#define STRINGBUFFERRL_H

#include <cstdlib>
#include <iostream>
#include <assert.h>
using namespace std;

#include "Test.h"

class StringBufferRL {

    // Allowing Test class to access private methods for testing
    friend class Test;
public:
    StringBufferRL();
    ~StringBufferRL();
    StringBufferRL(const StringBufferRL&);
    StringBufferRL(char*, int);
    char charAt(int) const;
    int length() const;
    void reserve(int);
    void append(char);

    /* Operater Overloading*/
    StringBufferRL& operator=(const StringBufferRL& that);

    void print();

private:
    char * _strbuf;
    int _length;
    int _bufsize;
    /* Reference linking specific attributes for maintaining doubly linked list*/
    mutable const StringBufferRL * next;
    mutable const StringBufferRL * prev;
    // Simple test : Create an object and see if it is alone
    bool isAlone();
    void release_resource();
    void acquire_resource(const StringBufferRL& that);
    void doAppend(char);

};

#endif /* STRINGBUFFERRL_H */

