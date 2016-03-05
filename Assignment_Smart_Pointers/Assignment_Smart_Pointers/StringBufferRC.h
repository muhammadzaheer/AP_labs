/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   StringBufferRC.h
 * Author: smzaheerabbas
 *
 * Created on March 4, 2016, 11:47 PM
 */

#ifndef STRINGBUFFERRC_H
#define STRINGBUFFERRC_H

#include <cstdlib>
#include <iostream>

using namespace std;

class StringBufferRC {
    
    friend class Test;
    
public:
    StringBufferRC();
    ~StringBufferRC();
    StringBufferRC(const StringBufferRC&);
    //TODO: Implement this
    StringBufferRC(char*, int);
    char charAt(int) const;
    int length() const;
    void reserve(int);
    void append(char);
    /* Operater Overloading*/
    StringBufferRC& operator=(const StringBufferRC& that);
    void print ();

private:
    /*
     * Nested class which points to the resource to be shared amongst StringBufferRC objects
     * It also keeps a count of StringBufferRC objects sharing the resource 
     */
    class Counter {
    public:
        char * _strbuf;
        int count;
    };

    Counter * _counter;

    int _length;
    int _bufsize;

    void doAppend(char);
    int return_count();
    
};



#endif /* STRINGBUFFERRC_H */

