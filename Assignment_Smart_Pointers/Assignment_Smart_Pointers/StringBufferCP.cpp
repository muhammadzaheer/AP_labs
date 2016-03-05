/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

#include <cstdlib>
#include <iostream>

#include "StringBufferCP.h"

using namespace std;

StringBufferCP::StringBufferCP() {
    _length = -1;
    _strbuf = NULL;
    _bufsize = -1;
}

StringBufferCP::~StringBufferCP() {
    if (_strbuf != NULL) {
        delete [] _strbuf;
    }
}

StringBufferCP::StringBufferCP(const StringBufferCP& that) {
    
    if (that._bufsize != NULL)
        this->_strbuf = new char[that._bufsize];
    this->_bufsize = that._bufsize;
    this->_length = that._length;

    for (int i = 0; i < that.length(); i++) {
        this->_strbuf[i] = that._strbuf[i];
    }

}

StringBufferCP::StringBufferCP(char * strbuf, int size) {

    this->_strbuf = strbuf;
    this->_bufsize = size;
    this->_length = 0;

}

char StringBufferCP::charAt(int index) const {

    if (index >= 0 && index < _length) {
        return _strbuf[index];
    }
    throw 'e';
}

int StringBufferCP::length() const {
    return _length;
}

void StringBufferCP::reserve(int size) {
    if (_strbuf != NULL) {
        delete [] _strbuf;
    }

    _strbuf = new char[size];
    _length = 0;
    _bufsize = size;
}

void StringBufferCP::append(char c) {
    if (_length < _bufsize) {
        _strbuf[_length] = c;
        _length++;
        return;
    }
    throw 'e';
}

StringBufferCP& StringBufferCP::operator=(const StringBufferCP& that) {

    if (this != &that) {

        /* Releasing previously held buffer if any*/
        if (this->_strbuf != NULL)
            delete [] this->_strbuf;
        
        if (that._strbuf != NULL) 
            this->_strbuf = new char[that._bufsize];
        else
            this->_strbuf = NULL;
        
       
        this->_bufsize = that._bufsize;

        for (int i = 0; i < that.length(); i++) {
            this->_strbuf[i] = that._strbuf[i];
        }

        this->_length = that._length;
    }

    return *this;
}

void StringBufferCP::print() {

    for (int i = 0; i < this->length(); i++) {
        cout << this->charAt(i);
    }
    cout << "\n";
}

