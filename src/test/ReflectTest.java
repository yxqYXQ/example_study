package test;

import org.junit.Test;

import java.lang.reflect.Method;

public class ReflectTest {
    @Test
    public void test1() throws ClassNotFoundException {
        Class clazz = Class.forName("reflect.Person");
        Method[] methods = clazz.getMethods();
        for (Method method :methods){
            System.out.println(method);
        }
    }
}
