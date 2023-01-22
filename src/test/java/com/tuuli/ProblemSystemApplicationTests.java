package com.tuuli;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tuuli.dao.TeaCourDao;
import com.tuuli.domain.TeaCour;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ProblemSystemApplicationTests {

    @Test
    void contextLoads() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("zhangsan");
        strings.add("lisi");
        strings.add("cws");
        strings.add("wr");
        System.out.println(strings);
    }

}
