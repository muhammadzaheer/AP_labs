/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   StringBufferOP.h
 * Author: smzaheerabbas
 *
 * Created on March 5, 2016, 12:39 AM
 */

#ifndef STRINGBUFFEROP_H
#define STRINGBUFFEROP_H

#include <cstdlib>
#include <iostream>

using namespace std;

class StringBufferOP {
    
    friend class Test;
    
public:
    StringBufferOP();
    ~StringBufferOP();
    StringBufferOP(const StringBufferOP&);
    StringBufferOP(char*, int);
    char charAt(int) const;
    int length() const;
    void reserve(int);
    void append(char);
    /* Operater Overloading*/
    StringBufferOP& operator=(const StringBufferOP& that);
    void print();
    
private:
    char * _strbuf;
    int _length;
    int _bufsize;
    mutable bool _isOwner;
    
    void release_ownership() const {
        this->_isOwner = false;
    }

    bool isOwner() {
        return this->_isOwner;
    }
};


#endif /* STRINGBUFFEROP_H */

