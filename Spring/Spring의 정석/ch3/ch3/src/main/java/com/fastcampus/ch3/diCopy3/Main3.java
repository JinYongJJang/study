package com.fastcampus.ch3.diCopy3;

import com.google.common.reflect.ClassPath;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Component class Car{}
@Component class SportsCar extends Car{}
@Component class Truck extends Car{}
@Component class Engine{}

class AppContext {
    Map map;  // 객체 저장소

    AppContext(){
        map = new HashMap();
        doComponentScan();
    }

    private void doComponentScan(){
        try {
            // 패키지 내의 클래스 목록 가져오기
            // 반복문으로 클래스를 하나씩 읽어와서 @Component가 붙어있는지 확인
            // @Component가 붙어있으면 객체를 생성해서 Map에 저장
            ClassLoader classLoader = AppContext.class.getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);

            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy3");

            for(ClassPath.ClassInfo classInfo : set){
                Class clazz = classInfo.load();
                Component component = (Component)clazz.getAnnotation(Component.class);
                if(component != null){
                    String id = StringUtils.uncapitalize(classInfo.getSimpleName());
                    map.put(id, clazz.newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        };

    }

    Object getBean(String key){  // by Name
        return map.get(key);
    }
    Object getBean(Class clazz){  // by Type
        for(Object obj : map.values()){
            if(clazz.isInstance(obj)){
                return obj;
            }
        }
        return null;
    }
}
public class Main3 {
    public static void main(String[] args) throws Exception {
        AppContext ac = new AppContext();

        //Car car = (Car)ac.getBean("car");
        Car car2 = (Car)ac.getBean(Car.class);
        Engine engine = (Engine)ac.getBean("engine");
        System.out.println("car = " + car2);
        System.out.println("engine = " + engine);
    }
}
