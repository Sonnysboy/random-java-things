package com.me.fpstuff.fpstuff.fp.types;



import com.me.fpstuff.fpstuff.fp.kinds.Kind1;

import java.util.function.Function;

public interface Functor<Mu> {


    <T, R> Kind1<Mu, R> fmap(Function<? super T, ? extends R> f, Kind1<Mu, T> x);

    /**
     * Replace all locations in the input with the same value.
     */
    default <T, R> Kind1<Mu, T> replaceAll(T a, Kind1<Mu, R> f) {
        return fmap($ -> a, f);
    }



}
