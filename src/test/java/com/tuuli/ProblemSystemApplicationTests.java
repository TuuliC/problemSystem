package com.tuuli;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest
class ProblemSystemApplicationTests {

    @Test
    void contextLoads() {
        File savePos = new File("src/main/resources/static/questionsImages");
        // 获取存放位置的规范路径
        try {
            String realPath = savePos.getCanonicalPath();
            System.out.println(realPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
