package com.me.fpstuff.fpstuff.fp.types;


import com.me.fpstuff.fpstuff.fp.kinds.Kind1;
import com.me.fpstuff.fpstuff.fp.other.Prelude;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface Applicative<Mu> extends Functor<Mu> {

    <T> Kind1<Mu, T> pure(T x);


    <T, R> Kind1<Mu, R> app(Kind1<Mu, Function<? super T, ? extends R>> f, Kind1<Mu, T> x);
//        return liftA2((a, t) -> unwrap(f).apply(t), x, x);

//    liftA2 f x y = f <$> x <*> y
    default <A, B, C> Kind1<Mu, C> liftA2(BiFunction<? super A, ? super B, ? extends C> f, Kind1<Mu, A> x, Kind1<Mu, B> y) {
        return app(fmap(Prelude.curry(f), x), y);
    };

    /** *>: Sequence actions, discarding the value of the first argument.
     *
     *
     */
    default <A, B> Kind1<Mu, B> sequenceAndDiscardFirst(Kind1<Mu, A> a1, Kind1<Mu, B> a2) {
        return app(replaceAll(x -> x, a1), a2);
    }
    /** Sequence actions, discarding the value of the second argument.
     */
    default <A, B> Kind1<Mu, A> sequenceAndDiscardSecond(Kind1<Mu, A> a1, Kind1<Mu, B> a2) {
        return liftA2((a, t) -> a, a1, a2);
    }



}
