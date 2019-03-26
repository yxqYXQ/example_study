package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class ShowMethods {

    private static String uages = "usage:\n reflect.ShowMethods qualified.class.name\n To showMethods qualified.class.name word \n To search for methods involing 'word'";
    private static Pattern p = Pattern.compile("\\w+\\.");
    public static void main(String args[]){
        if(args.length < 1){
            System.out.println(uages);
            System.exit(0);
        }
        int lines = 0;
        try {
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor[] ctors = c.getConstructors();
            if(args.length == 1){
                for (Method method :methods){
                    System.out.println(p.matcher(method.toString()).replaceAll(""));
                }
                for (Constructor ctor :ctors){
                    System.out.println(p.matcher(ctor.toString()).replaceAll(""));
                }
                lines = methods.length+ctors.length;
            }else{
                for (Method method :methods){
                    if(method.toString().indexOf(args[1])!= -1){
//                        System.out.println(p.matcher(method.toString()).replaceAll(""));
                        System.out.println(method.toString()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            );
                        lines++;
                    }

                }
                for (Constructor ctor :ctors){
                    if(ctor.toString().indexOf(args[1])!= -1) {
                        System.out.println(p.matcher(ctor.toString()).replaceAll(""));
                        lines++;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
