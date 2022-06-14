package com.fastcampus.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

class Car{}
class Engine{}
class Door{}

public class SpringDiTest {
    public static void main(String[] args) {
        ApplicationContext ac= new GenericXmlApplicationContext("config.xml");
        Car car = (Car) ac.getBean("car"); // by Name
        //Car car1 = ac.getBean("car", Car.class); // by Name  // 형변환이 필요없음
        Car car2 = (Car) ac.getBean(Car.class); // by Type
        Engine engine = (Engine) ac.getBean("engine");
        Door door = (Door) ac.getBean("door");

        System.out.println("car = " + car);
        System.out.println("car2 = " + car2);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);
    }

}
