package com.me.fpstuff.fpstuff.fp.other;


import com.me.fpstuff.fpstuff.fp.control.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Prelude {

    public static <T> T id(T x) { return x; }

    public static <T, U, V> Function<T, Function<U, V>> curry(BiFunction<? super T, ? super U, ? extends V> f) {
        return (T t) -> (U u) -> f.apply(t, u);

    }
    public static <T, U, V, W> Function<T, Function<U, Function<V, W>>> curry(TriFunction<? super T, ? super U, ? super V, ? extends W> f) {
        return (T t) -> (U u) -> (V v) -> f.apply(t, u, v);

    }
}
