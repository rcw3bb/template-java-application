package xyz.ronella.template.app.commons;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Invoker {

    private Invoker() {}

    public static <TYPE> void execute(Consumer<TYPE> logic, TYPE param) {
        logic.accept(param);
    }

    public static <TYPE> void execute(Consumer<TYPE> logic) {
        execute(logic, null);
    }

    public static <TYPE_ARG1, TYPE_ARG2, TYPE_RETURN>
    TYPE_RETURN execute(BiFunction<TYPE_ARG1, TYPE_ARG2, TYPE_RETURN> logic, TYPE_ARG1 arg1, TYPE_ARG2 arg2) {
        return logic.apply(arg1, arg2);
    }

    public static <TYPE_ARG, TYPE_RETURN> TYPE_RETURN execute(Function<TYPE_ARG, TYPE_RETURN> logic, TYPE_ARG arg) {
        return logic.apply(arg);
    }

}
