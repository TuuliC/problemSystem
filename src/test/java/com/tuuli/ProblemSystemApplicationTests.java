package com.tuuli;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class ProblemSystemApplicationTests {

    @Test
    void contextLoads() {
       String[] strings = {"1，21,1","222"};
        System.out.println(Arrays.toString(strings));
    }

}
