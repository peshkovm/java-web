import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        Class<A> cl = A.class; // Получение объекта класса
        A object = cl.newInstance(); // Создание нового объекта из объекта класса
        System.out.println("class:" + cl.getName()); // Получение имени класса
        System.out.println("");

// Все публичные методы класса (включая предков)
        for (Method method : cl.getMethods()) {
            System.out.print("  " + method.getName());
            System.out.println(Arrays.stream(method.getParameterTypes()).map(c -> c.getName()).collect(Collectors.joining(",", "(", ")"))
            );
        }

        System.out.println("");

// Все методы объявленные в классе
        for (Method method : cl.getDeclaredMethods()) {
            System.out.print("  " + method.getName());
            System.out.println(Arrays.stream(method.getParameterTypes()).map(c -> c.getName()).collect(Collectors.joining(",", "(", ")"))
            );
        }

        System.out.println("");

        for (Field method : cl.getFields()) {
            System.out.print("  " + method.getType() + " ");
            System.out.println(method.getName());
        }
    }
}