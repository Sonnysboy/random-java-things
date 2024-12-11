package com.me.diabolicalideas;

import java.util.function.Function;

public class CPS {

    <U> U add(double x, double y, Function<Double, U> cont) {
        return cont.apply(x + y);
    }
    <U> U square(double x, Function<Double, U> cont) {
        return cont.apply(x * x);
    }
    <U> U sqrt(double x, Function<Double, U> cont) {
        return cont.apply(Math.sqrt(x));
    }
    <U> U hypot(double x, double y, Function<Double, U> cont) {
        return square(x, x2 -> square(y, y2 -> add(x2, y2, a -> sqrt(a, cont))));
    }
    public static void main(String[] args) {
        new CPS().hypot(4, 3, x -> System.out.printf("%f", x));
    }
}
