/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
#include <cstdlib>
#include <iostream>
#include "StringBufferOP.h"

using namespace std;

StringBufferOP::StringBufferOP() {
    _length = -1;
    _strbuf = NULL;
    _bufsize = -1;
    _isOwner = true;
}

StringBufferOP::~StringBufferOP() {
    if (_strbuf != NULL && _isOwner) {
        //cout << "Deleting strbuf " << &_strbuf;
        delete [] _strbuf;
    }
}

StringBufferOP::StringBufferOP(const StringBufferOP& that) {

    if (this != &that) {

        this->_isOwner = that._isOwner;
        this->_strbuf = that._strbuf;
        this->_bufsize = that._bufsize;
        this->_length = that._length;

        that.release_ownership();

    }
}

StringBufferOP::StringBufferOP(char * strbuf, int size) {

    this->_strbuf = strbuf;
    this->_bufsize = size;
    this->_length = 0;
    this->_isOwner = true;
    
}

char StringBufferOP::charAt(int index) const {

    if (index >= 0 && index < _length) {
        return _strbuf[index];
    }
    throw 'e';
}

int StringBufferOP::length() const {
    return _length;
}

void StringBufferOP::reserve(int size) {
    if (_strbuf != NULL) {
        // If this object was the owner of previously held strbuf, release
        if (_isOwner)
            delete [] _strbuf;
    }

    _strbuf = new char[size];
    _length = 0;
    _bufsize = size;
    
    // Since reserve allocates a fresh strbuf, this object should be the owner 
    _isOwner = true;
}

void StringBufferOP::append(char c) {
    if (_length < _bufsize) {
        _strbuf[_length] = c;
        _length++;
        return;
    }
    
    throw 'e';
}

StringBufferOP& StringBufferOP::operator=(const StringBufferOP& that) {
    if (this != &that) {
        // If this and that don't already point to the same strbuf
        if (this->_strbuf != that._strbuf) {
            /* Releasing previously hold strbuf for this if its the owner
               Since this will becoming the owner of new strbuf */
            if (this->_isOwner && this->_strbuf != NULL) {
                delete [] this->_strbuf;
            }
        }
        this->_isOwner = that._isOwner;
        this->_strbuf = that._strbuf;
        this->_bufsize = that._bufsize;
        this->_length = that._length;

        that.release_ownership();

    }
    return *this;
}

void StringBufferOP::print () {

    for (int i = 0; i < this->length(); i++) {
        cout << this->charAt(i);
    }
    cout << "\n";
}