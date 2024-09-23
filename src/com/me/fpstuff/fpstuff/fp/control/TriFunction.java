package com.me.fpstuff.fpstuff.fp.control;

import java.util.Objects;
import java.util.function.Function;

public interface TriFunction<T, U, V, W> {


    W apply(T t, U u, V v);

    default <X> TriFunction<T, U, V, X> andThen(Function<? super W, ? extends X> after) {
        Objects.requireNonNull(after);
        return (T t, U u, V v) -> after.apply(apply(t, u, v));
    }

}
