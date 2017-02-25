package by.epam.jmp.backend.classloading;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.Scanner;

public final class JMP7_4_Backend_Classloading {

    private JMP7_4_Backend_Classloading() { }

    public static void main(String[] args) throws Exception {
        ClassLoader loader = ArrayUtils.getLength(args) > 1 ? new CustomClassloader(args[0]) : new CustomClassloader("./others");
        Class<?> clazz = loader.loadClass(ArrayUtils.getLength(args) > 1 ? args[1] : "by.epam.jmp.backend.classloading.Semaphore");

        Object semaphore = clazz.newInstance();

        for (Method method : clazz.getDeclaredMethods()) {
            method.invoke(semaphore);
        }

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Do you want reload? (y/n)");
            String result = scanner.next();
            if ("y".equalsIgnoreCase(result)) {
                System.out.println("Enter path: ");
                result = scanner.next();
                loader = new CustomClassloader(result);
                clazz = loader.loadClass(ArrayUtils.getLength(args) > 1 ? args[1] : "by.epam.jmp.backend.classloading.Semaphore");

                semaphore = clazz.newInstance();

                for (Method method : clazz.getDeclaredMethods()) {
                    method.invoke(semaphore);
                }
            } else {
                exit = true;
            }
        }
    }

}
