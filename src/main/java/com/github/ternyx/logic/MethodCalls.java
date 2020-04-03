package com.github.ternyx.logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import com.github.ternyx.main.Main;
import com.github.ternyx.structures.Queue;

/**
 * MethodCalls
 */
public class MethodCalls {
    private Queue<MethodEntity> methods = new Queue<>();

    private class MethodEntity {
        private String methodName;
        private Class targetClass;
        private Object[] arguments;

        public MethodEntity(String methodName, Class targetClass, Object[] arguments) {
            this.methodName = methodName;
            this.targetClass = targetClass;
            this.arguments = arguments;
        }

        public String getMethodName() {
            return methodName;
        }
        public Class getTargetClass() {
            return targetClass;
        }
        public Object[] getArguments() {
            return arguments;
        }
    }

    public MethodCalls() {}

    public boolean enqueueMethod(String methodName, Class targetClass, Object[] arguments) {
        return methods.enqueue(new MethodEntity(methodName, targetClass, arguments));
    }

    // default to main
    public boolean enqueueMethod(String methodName, Object[] arguments) {
        return methods.enqueue(new MethodEntity(methodName, Main.class, arguments));
    }

    public boolean enqueueMethod(String methodName) {
        return methods.enqueue(new MethodEntity(methodName, Main.class, null));
    }

    public void executeMethods() {
        while (!methods.isEmpty()) {
            MethodEntity current = methods.dequeue();

            Object[] arguments = current.getArguments();
            String methodName = current.getMethodName();
            Class targetClass = current.getTargetClass();

            Class[] parameterTypes = null;
            if (arguments != null) {
                parameterTypes =  Arrays.stream(arguments)
                    .map(Object::getClass)
                    // 
                    // unwrap primitives
                    //
                    .map(c -> c == Integer.class ? int.class : c)
                    .toArray(Class[]::new);
            }

            try {
                if (parameterTypes == null) {
                    Method foundMethod = targetClass.getMethod(methodName);
                    foundMethod.invoke(targetClass);
                } else {
                    Method foundMethod = targetClass.getMethod(methodName, parameterTypes);
                    foundMethod.invoke(targetClass, arguments);
                }
            } catch (NoSuchMethodException e) {
                System.out.format("Couldn't find %s method in %s with %s argument types\n",
                        methodName, targetClass, Arrays.toString(parameterTypes));
            } catch (IllegalAccessException | InvocationTargetException ex) {
                System.out.format("Couldn't invoke %s method", methodName);
            }
        }
    }
}
