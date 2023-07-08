package com.usman.forum;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class ForumApplicationTests {

    Addition addition= new Addition();

    @Test
    void AddNumber() {

//        before
        int a=9;
        int b=1;
//        when
        int sum= addition.add(a, b);
//        after
        assertThat(sum).isEqualTo(10);
    }



    class Addition{
        int add(int a , int b){
            return  a+b;
        }
    }



        }
