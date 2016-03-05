/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Test.cpp
 * Author: smzaheerabbas
 * 
 * Created on March 4, 2016, 5:53 PM
 */

#include <iostream>
#include <assert.h>
#include "Test.h"
#include "StringBufferCP.h"
#include "StringBufferOP.h"
#include "StringBufferRL.h"
#include "StringBufferRC.h"

using namespace std;

Test::Test() {

}

void Test::string_buffer_CP() {
    
    cout << "Testing StringBuffer with Copied Pointer\n";
    test_copy_constructor_CP();
    test_length_CP();
    test_charAt_CP();
    test_append_CP();
    test_deep_copy();
    cout << "Tests passed successfully\n";

}

void Test::test_copy_constructor_CP() {

    StringBufferCP sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 10; i++) {
        sb.append(i);
    }

    StringBufferCP copy(sb);

    for (int i = 0; i < 10; i++) {
        assert(sb.charAt(i) == copy.charAt(i));
    }

    assert(sb._strbuf != copy._strbuf);

}

void Test::test_length_CP() {

    StringBufferCP sb;

    assert(sb.length() == -1);
    sb.reserve(10);
    assert(sb.length() == 0);
    sb.append('z');
    assert(sb.length() == 1);
    sb.append('a');
    assert(sb.length() == 2);
    
    StringBufferCP copy;
    sb = copy;
    assert(sb.length() == -1);
    
     
}

void Test::test_charAt_CP() {
    
    StringBufferCP sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 10; i++) {
        sb.append(i);
    }

    for (int i = 0; i < 10; i++) {
        assert(sb.charAt(i) == (char) 'a' + i);
    }

    // Testing exception for out of bound charAt

    bool exception = false;
    try {
        sb.charAt(11);
    } catch (char c) {
        if (c == 'e')
            exception = true;
    }

    assert(exception);

    exception = false;
    try {
        sb.charAt(-1);
    } catch (char c) {
        if (c == 'e')
            exception = true;
    }

    assert(exception);


}

void Test::test_append_CP() {
    
    StringBufferCP sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 10; i++) {
        sb.append(i);
        // Testing if length is appropriately incremented
        assert(sb.length() == i - 'a' + 1);
    }

    for (int i = 0; i < 10; i++) {
        assert(sb.charAt(i) == (char) 'a' + i);
    }

    // Testing exception for out of bound append
    bool exception = false;
    try {
        sb.append('z');
    } catch (char c) {
        if (c == 'e')
            exception = true;
    }

    assert(exception);


}

void Test::test_deep_copy() {

    StringBufferCP sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 5; i++) {
        sb.append(i);
    }
    
    StringBufferCP copy;
    copy = sb;
    assert(sb._strbuf != copy._strbuf);

    StringBufferCP copy_2(copy);
    assert(sb._strbuf != copy._strbuf && copy._strbuf != copy_2._strbuf);
    
    for (int i = 0; i < sb.length(); i++) {
        assert(sb.charAt(i) == copy.charAt(i));
        assert(copy.charAt(i) == copy_2.charAt(i));
    }
    
}

void Test::string_buffer_OP() {
    cout << "Testing StringBuffer with Owned Pointer\n";
    test_copy_constructor_OP();
    test_length_OP();
    test_charAt_OP();
    test_append_OP();
    test_own_transfer();
    cout << "Tests passed successfully\n";
}

void Test::test_copy_constructor_OP() {

    StringBufferOP sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 10; i++) {
        sb.append(i);
    }

    assert(sb.isOwner());
    StringBufferOP copy(sb);

    for (int i = 0; i < 10; i++) {
        assert(sb.charAt(i) == copy.charAt(i));
    }

    assert(!sb.isOwner());
    assert(copy.isOwner());

    assert(sb._strbuf == copy._strbuf);

}

void Test::test_length_OP() {

    StringBufferOP sb;

    assert(sb.length() == -1);
    sb.reserve(10);
    assert(sb.length() == 0);
    sb.append('z');
    assert(sb.length() == 1);
    sb.append('a');
    assert(sb.length() == 2);

    StringBufferOP copy;
    sb = copy;
    assert(sb.length() == -1);

}

void Test::test_charAt_OP() {
    
    StringBufferOP sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 10; i++) {
        sb.append(i);
    }

    for (int i = 0; i < 10; i++) {
        assert(sb.charAt(i) == (char) 'a' + i);
    }

    // Testing exception for out of bound charAt

    bool exception = false;
    try {
        sb.charAt(11);
    } catch (char c) {
        if (c == 'e')
            exception = true;
    }

    assert(exception);

    exception = false;
    try {
        sb.charAt(-1);
    } catch (char c) {
        if (c == 'e')
            exception = true;
    }

    assert(exception);

}

void Test::test_append_OP() {    
    
    StringBufferOP sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 10; i++) {
        sb.append(i);
        // Testing if length is appropriately incremented
        assert(sb.length() == i - 'a' + 1);
    }

    for (int i = 0; i < 10; i++) {
        assert(sb.charAt(i) == (char) 'a' + i);
    }

    // Testing exception for out of bound append
    bool exception = false;
    try {
        sb.append('z');
    } catch (char c) {
        if (c == 'e')
            exception = true;
    }

    assert(exception);

}

void Test::test_own_transfer() {

    StringBufferOP sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 5; i++) {
        sb.append(i);
    }
    
    assert(sb.isOwner());
    StringBufferOP copy;
    copy = sb;
    assert(!sb.isOwner() && copy.isOwner() && sb._strbuf == copy._strbuf);

    StringBufferOP copy_2(copy);
    assert(!sb.isOwner() && !copy.isOwner() && copy_2.isOwner() && sb._strbuf == copy._strbuf
            && copy._strbuf == sb._strbuf);
}

void Test::string_buffer_RC() {

    cout << "Testing StringBuffer with Reference Counting \n";
    test_copy_constructor_RC();
    test_length_RC();
    test_charAt_RC();
    test_append_RC();
    test_COW_RC();
    test_destructor_RC();
    cout << "Tests passed successfully\n";

}

void Test::test_copy_constructor_RC() {

    StringBufferRC sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 10; i++) {
        sb.append(i);
    }

    assert(sb.return_count() == 1);

    StringBufferRC copy(sb);

    for (int i = 0; i < 10; i++) {
        assert(sb.charAt(i) == copy.charAt(i));
    }

    /* Testing that sb are sharing the same resource (with count == 2) */
    assert(sb.return_count() == 2);
    assert(copy.return_count() == 2);
    assert(sb._counter->_strbuf == copy._counter->_strbuf);
    assert(sb._counter == copy._counter);

}

void Test::test_length_RC() {

    StringBufferRC sb;

    assert(sb.length() == -1);
    sb.reserve(10);
    assert(sb.length() == 0);
    sb.append('z');
    assert(sb.length() == 1);
    sb.append('a');
    assert(sb.length() == 2);

	
    StringBufferRC copy;
    sb = copy;
    assert(sb.length() == -1);
	
}

void Test::test_charAt_RC() {

    StringBufferRC sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 10; i++) {
        sb.append(i);
    }

    for (int i = 0; i < 10; i++) {
        assert(sb.charAt(i) == (char) 'a' + i);
    }

    // Testing exception for out of bound charAt

    bool exception = false;
    try {
        sb.charAt(11);
    } catch (char c) {
        if (c == 'e')
            exception = true;
    }

    assert(exception);

    exception = false;
    try {
        sb.charAt(-1);
    } catch (char c) {
        if (c == 'e')
            exception = true;
    }

    assert(exception);


}

void Test::test_append_RC() {

    StringBufferRC sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 10; i++) {
        sb.append(i);
        // Testing if length is appropriately incremented
        assert(sb.length() == i - 'a' + 1);
    }

    for (int i = 0; i < 10; i++) {
        assert(sb.charAt(i) == (char) 'a' + i);
    }

    // Testing exception for out of bound append
    bool exception = false;
    try {
        sb.append('z');
    } catch (char c) {
        if (c == 'e')
            exception = true;
    }

    assert(exception);
}

void Test::test_COW_RC() {

    StringBufferRC sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 5; i++) {
        sb.append(i);
    }

    StringBufferRC copy;
    copy = sb;
    assert(sb.return_count() == 2 && copy.return_count() == 2);
    assert(sb._counter == copy._counter);
	
    StringBufferRC copy_2(copy);
    assert(sb.return_count() == 3 && copy.return_count() == 3 && copy_2.return_count() == 3);
    assert(sb._counter == copy._counter && copy._counter == copy_2._counter);



    sb.append('x');
    assert(sb.return_count() == 1 && copy.return_count() == 2 && copy_2.return_count() == 2);
    assert(sb._counter != copy._counter && copy._counter == copy_2._counter);
    copy.append('x');
    assert(copy.return_count() == 1 && copy_2.return_count() == 1);
    assert(sb._counter != copy._counter && copy._counter != copy_2._counter); 
}

void Test::test_destructor_RC() {

    StringBufferRC sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 5; i++) {
        sb.append(i);
    }

	{
		StringBufferRC copy;
		copy = sb;
		{
			StringBufferRC copy_2(copy);
			assert(sb.return_count() == 3);
		}
		assert(sb.return_count() == 2);
	}
	assert(sb.return_count() == 1);
	

}

void Test::string_buffer_RL() {
    cout << "Testing StringBuffer with Reference Linking \n";

    test_length_RL();
    test_copy_constructor_RL();
    test_charAt_RL();
    test_append_RL();
    test_COW_RL();
    test_destructor_RL();

    cout << "Tests passed successfully\n";

    return;
}

void Test::test_copy_constructor_RL() {

    StringBufferRL sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 10; i++) {
        sb.append(i);
    }

    StringBufferRL copy(sb);

    for (int i = 0; i < 10; i++) {
        assert(sb.charAt(i) == copy.charAt(i));
    }

    /* Testing that sb and copy are not alone and are pointing to the same resource */
    assert(!sb.isAlone());
    assert(!copy.isAlone());
    assert(sb._strbuf == copy._strbuf);

}

void Test::test_charAt_RL() {
    StringBufferRL sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 10; i++) {
        sb.append(i);
    }

    for (int i = 0; i < 10; i++) {
        assert(sb.charAt(i) == (char) 'a' + i);
    }

    // Testing exception for out of bound charAt

    bool exception = false;
    try {
        sb.charAt(11);
    } catch (char c) {
        if (c == 'e')
            exception = true;
    }

    assert(exception);

    exception = false;
    try {
        sb.charAt(-1);
    } catch (char c) {
        if (c == 'e')
            exception = true;
    }

    assert(exception);
}

void Test::test_append_RL() {

    StringBufferRL sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 10; i++) {
        sb.append(i);
        // Testing if length is appropriately incremented
        assert(sb.length() == i - 'a' + 1);
    }

    for (int i = 0; i < 10; i++) {
        assert(sb.charAt(i) == (char) 'a' + i);
    }

    // Testing exception for out of bound append
    bool exception = false;
    try {
        sb.append('z');
    } catch (char c) {
        if (c == 'e')
            exception = true;
    }

    assert(exception);

}

void Test::test_length_RL() {
    StringBufferRL sb;

    assert(sb.length() == -1);
    sb.reserve(10);
    assert(sb.length() == 0);
    sb.append('z');
    assert(sb.length() == 1);
    sb.append('a');
    assert(sb.length() == 2);

    StringBufferRL copy;
    sb = copy;
    assert(sb.length() == -1);

}

/*
 * Comprehensive Test: Testing Reference linking and copy on write
 * In turn tests release_resource() and acquire_resource by pointer comparison
 */
void Test::test_COW_RL() {

    StringBufferRL sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 5; i++) {
        sb.append(i);
    }

    StringBufferRL copy;
    copy = sb;
    assert(sb.isAlone() == 0 && sb.isAlone() == copy.isAlone());

    StringBufferRL copy_2(copy);
    assert(sb.isAlone() == 0 && sb.isAlone() == copy.isAlone() && sb.isAlone() == copy_2.isAlone());

    // Testing doubly linked-list forward and backward connections
    assert(sb.next == &copy && copy.next == &copy_2 && copy_2.next == &sb);
    assert(sb.prev == &copy_2 && copy.prev == &sb && copy_2.prev == &copy);

    sb.append('x');
    assert(sb.isAlone() == 1 && copy.isAlone() == 0 && copy.isAlone() == 0);
    assert(copy.next == &copy_2 && copy.prev == &copy_2);
    assert(copy.prev == &copy_2 && copy.prev == &copy_2);

    copy.append('x');
    assert(copy.isAlone() == 1 && copy_2.isAlone() == 1);
}

void Test::test_destructor_RL() {

    StringBufferRL sb;
    sb.reserve(10);

    for (char i = 'a'; i < 'a' + 5; i++) {
        sb.append(i);
    }
	{
		StringBufferRL copy;
		copy = sb;
	}
    StringBufferRL copy_2(sb);
	
    assert(sb.next == &copy_2 && copy_2.next == &sb);
    assert(sb.prev == &copy_2 && copy_2.prev == &sb);

}


\
