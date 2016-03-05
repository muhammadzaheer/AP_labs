/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
#include <cstdlib>
#include <iostream>
#include <assert.h>

#include "StringBufferRL.h"

using namespace std;

StringBufferRL::StringBufferRL() {
    _length = -1;
    _strbuf = NULL;
    _bufsize = -1;
    next = this;
    prev = this;
}

StringBufferRL::~StringBufferRL() {

    if (this->isAlone()) {
        if (this->_strbuf != NULL)
            delete [] _strbuf;
    } else {
        this->release_resource();
    }
}

StringBufferRL::StringBufferRL(const StringBufferRL& that) {

    // Putting this object in 'that' object's doubly-linked list
    this->acquire_resource(that);
    this->_strbuf = that._strbuf;
    this->_bufsize = that._bufsize;
    this->_length = that._length;

}

StringBufferRL::StringBufferRL(char * strbuf, int size) {

    this->_strbuf = strbuf;
    this->_bufsize = size;
    this->_length = 0;
    next = this;
    prev = this;
    
}

char StringBufferRL::charAt(int index) const {

    if (index >= 0 && index < _length) {
        return _strbuf[index];
    }
    throw 'e';
}

int StringBufferRL::length() const {
    return _length;
}

void StringBufferRL::reserve(int size) {
    if (this->isAlone()) {
        if (this->_strbuf != NULL)
            delete [] _strbuf;
    } else {
        this->release_resource();
    }

    _strbuf = new char[size];
    _length = 0;
    _bufsize = size;
    next = this;
    prev = this;
}

void StringBufferRL::append(char c) {
    /*
     * For obj.append('x');
     * Case - I: obj is the sole owner of resource
     * Simply append and return
     */
    if (isAlone()) {
        doAppend(c);
        return;
    }
    
    /*
     * Case - II: obj is sharing resource with another object. 
     * Create a copy of previous resource and append in it and release previous resource
     */
    
    char * holder_strbuf = new char[_bufsize];
    for (int i = 0; i < _length; i++)
        holder_strbuf[i] = this->_strbuf[i];
    
    /* 
     * We've now copied previous resource into holder_strbuf, let's forget about the
     * previous resource
     */
    this->release_resource();
    this->_strbuf = holder_strbuf;
    this->next = this->prev = this;
    doAppend(c);
}

void StringBufferRL::doAppend(char c) {
    
    if (_length < _bufsize) {
        _strbuf[_length] = c;
        _length++;
        return;
    }
    throw 'e';
}

StringBufferRL& StringBufferRL::operator=(const StringBufferRL& that) {

    if (this != &that) {

        /* Release the resource if this object is the only user*/
        if (this->isAlone()) {
            if (this->_strbuf != NULL)
                delete [] this->_strbuf;
        } else { // Delete itself from the reference list
            this->release_resource();
        }

        // Putting this object in 'that' object's doubly-linked list
        this->acquire_resource(that);
        this->_strbuf = that._strbuf;
        this->_bufsize = that._bufsize;
        this->_length = that._length;
    }

    return *this;

}

bool StringBufferRL::isAlone() {
    if (this->prev == this)
        return true;
    else
        return false;
}

void StringBufferRL::release_resource() {

    assert(!isAlone());
    this->prev->next = this->next;
    this->next->prev = this->prev;

}

void StringBufferRL::acquire_resource(const StringBufferRL& that) {

    that.next->prev = this;
    this->next = that.next;

    that.next = this;
    this->prev = &that;
}

void StringBufferRL::print() {

    for (int i = 0; i < this->length(); i++) {
        cout << this->charAt(i);
    }
    cout << "\n";
}


