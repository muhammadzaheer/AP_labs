/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Test.h
 * Author: smzaheerabbas
 *
 * Created on March 4, 2016, 5:53 PM
 */

#ifndef TEST_H
#define TEST_H

class Test {
public:
    
    Test();
    void string_buffer_CP();
    void string_buffer_OP();
    void string_buffer_RC();
    void string_buffer_RL();
    

private:
    
    void test_copy_constructor_CP();
    void test_length_CP();
    void test_charAt_CP();
    void test_append_CP();
    void test_deep_copy();
    
    void test_copy_constructor_OP();
    void test_length_OP();
    void test_charAt_OP();
    void test_append_OP();
    void test_own_transfer();
    
    void test_copy_constructor_RC();
    void test_length_RC();
    void test_charAt_RC();
    void test_append_RC();
    void test_COW_RC();
    void test_destructor_RC();
    
    void test_copy_constructor_RL();
    void test_length_RL();
    void test_charAt_RL();
    void test_append_RL();
    void test_COW_RL();
    void test_destructor_RL();
    

};

#endif /* TEST_H */

