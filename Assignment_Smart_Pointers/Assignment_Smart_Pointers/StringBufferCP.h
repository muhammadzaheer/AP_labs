/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   StringBufferCP.h
 * Author: smzaheerabbas
 *
 * Created on March 5, 2016, 1:17 AM
 */

#ifndef STRINGBUFFERCP_H
#define STRINGBUFFERCP_H

#include <cstdlib>
#include <iostream>

using namespace std;

class StringBufferCP {
    
    friend class Test;
    
public:
    StringBufferCP();
    ~StringBufferCP();
    StringBufferCP (const StringBufferCP&);
    StringBufferCP(char*, int);
    char charAt(int) const;
    int length() const;
    void reserve(int);
    void append(char);
    void print ();
    
    /* Operater Overloading*/
    StringBufferCP& operator= (const StringBufferCP& that);
    
private:
    char * _strbuf;
    int _length;
    int _bufsize;
};


#endif /* STRINGBUFFERCP_H */

