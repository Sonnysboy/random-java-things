package com.me.fpstuff.fpstuff.fp.kinds;

import java.util.Objects;
import java.util.Optional;

//todo refactor into just and nothing
public class Maybe<T> implements Kind1<Maybe.Mu, T> {

    private static final Maybe<?> NOTHING = new Maybe<>(null);

    private final T value;


    public Maybe(T t) {
        this.value = t;
    }

    @SuppressWarnings("unchecked")
    public static <T> Maybe<T> nothing() {
        return (Maybe<T>) NOTHING;
    }


    public static final class Mu {}

    public static <T> Maybe<T> narrow(Kind1<Mu, T> x) {
        return (Maybe<T>)x;
    }

    public static <T> Maybe<T> widen(Optional<T> x) {
        return new Maybe<>(x.orElse(null));
    }

    /** Widen a <code>t</code> into a Maybe value.
     *
     */
    public static <T> Maybe<T> widen(T t) {
        return new Maybe<>(t);
    }
    public T unwrap() {
        return this.value;
    }
    public static boolean isJust(Maybe<?> m) {
        return m.value != null;
    }
    public boolean isJust() {
        return isJust(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maybe<?> maybe = (Maybe<?>) o;
        return Objects.equals(value, maybe.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
