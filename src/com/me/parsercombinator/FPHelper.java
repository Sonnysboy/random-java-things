package com.me.parsercombinator;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FPHelper {

    public static <A, B> B maybe(B defaultValue, Function<A, B> f, Optional<A> m) {
        return m.map(f)
                .orElse(defaultValue);
    }
    public static <T,U> Function<T,U> intoFunction(Function<T, U> f) { return f; }
    public static <T, U, V> Function<T, Function<U, V>> curry(BiFunction<? super T, ? super U, ? extends V> f) {
        return (T t) -> (U u) -> f.apply(t, u);

    }

}
