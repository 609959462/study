package com.ppf.frame;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FrameApplicationTests {
    class Person {
        private int age;
        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @Test
    void contextLoads() {

        test1();

    }
    public String test1(){
        Person p = new Person();
        p.age = 1;
        p.name = "2";
        test2(p);
        System.out.println("测试年龄：" + p.age + p.name);
        return "";
    }
    public String test2(Person p){
        p.age=2;
        p.name="1222";
        return "";
    }
}
