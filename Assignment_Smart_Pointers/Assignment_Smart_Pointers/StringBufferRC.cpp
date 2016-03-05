/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
#include <cstdlib>
#include <iostream>

#include "StringBufferRC.h"

using namespace std;

StringBufferRC::StringBufferRC() {
    _length = -1;
    _counter = new Counter();
    _counter->_strbuf = NULL;
    _counter->count = 1;
    _bufsize = -1;
}

StringBufferRC::~StringBufferRC() {

    _counter->count--;

    if (_counter->count == 0) {
        if (_counter->_strbuf != NULL)
            delete [] _counter->_strbuf;
        delete _counter;
    }
}

StringBufferRC::StringBufferRC(const StringBufferRC& that) {

    this->_counter = that._counter;
    this->_counter->count++;
    this->_length = that._length;
    this->_bufsize = that._bufsize;

}

StringBufferRC::StringBufferRC(char * strbuf, int size) {
    
    this->_counter = new Counter();
    this->_counter->_strbuf = strbuf;
    this->_bufsize = size;
    this->_length = 0;
    this->_counter->count = 1;
    
}

char StringBufferRC::charAt(int index) const {

    if (index >= 0 && index < _length) {
        return this->_counter->_strbuf[index];
    }
    throw 'e';
}

int StringBufferRC::length() const {
    return _length;
}

void StringBufferRC::reserve(int size) {
    if (_counter->_strbuf != NULL) {
        if (_counter->count == 1)
            delete [] _counter->_strbuf;
        else {  // We are reserving a new resource, so decrementing the count of previous one
            _counter->count--;
            _counter = new Counter();
        }
    }

    _counter->_strbuf = new char[size];
    _counter->count = 1;
    _length = 0;
    _bufsize = size;
}

void StringBufferRC::append(char c) {
    /*
     * For a.append('x');
     * Case - I: a is the sole owner of resource
     * Simply append
     */

    if (this->_counter->count == 1) {
        doAppend(c);
        return;
    }
    
    //TODO: Assert this->counter->count > 1
    
    /*
     * Case - II: a is sharing resource with some other object
     * Create a new copy of the resource and append. Decrement the count
     * for previous resource
     */
    
    this->_counter->count--;
    char * holder_strbuf = new char[_bufsize];
    for (int i = 0; i < _length; i++)
        holder_strbuf[i] = this->_counter->_strbuf[i];
    
    /* 
     * We've now copied previous resource into holder_strbuf, let's forget about the
     * previous resource
     */
    this->_counter = new Counter();
    this->_counter->_strbuf = holder_strbuf;
    this->_counter->count = 1; 
    
    doAppend(c);
    
}

void StringBufferRC::doAppend(char c) {
    if (_length < _bufsize) {
        _counter->_strbuf[_length] = c;
        _length++;
        return;
    }

    throw 'e';
}

/*
 * For A = B
 * Let 'this' be 'A' and 'that' be 'B'
 */
StringBufferRC& StringBufferRC::operator=(const StringBufferRC& that) {

    // A and B are same StringBuffer 
    if (this == &that)
        return *this;

    // A and B are readonly copies of same resource 
    if (this->_counter->_strbuf == that._counter->_strbuf) {
        return *this;
    }

    /* A is the sole user of the resource thus deleting resource as
     * A would be sharing onto B's resource
     */

    if (this->_counter->count == 1) {
		if (this->_counter->_strbuf != NULL)
			delete [] this->_counter->_strbuf;
		delete this->_counter;
    }



    this->_counter = that._counter;
    this->_counter->count++;
    this->_length = that._length;
    this->_bufsize = that._bufsize;
    return *this;
}

void StringBufferRC::print() {

    for (int i = 0; i < this->length(); i++) {
        cout << this->charAt(i);
    }
    cout << "\n";
}

int StringBufferRC::return_count() {
        return this->_counter->count;
}