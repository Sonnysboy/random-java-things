package com.me.fpstuff.fpstuff.fp.types;



import com.me.fpstuff.fpstuff.fp.kinds.Kind1;

import java.util.function.Function;

public interface Monad<Mu> extends Applicative<Mu> {


//    (urn)

    /** Inject a value into the monadic type.
     */
    default <T> Kind1<Mu, T> ret(T x) { return pure(x); }

    <T, U> Kind1<Mu, U> bind(Kind1<Mu, T> ma, Function<? super T, ? extends Kind1<Mu, U>> f);

    /**  Sequentially compose two actions, discarding any value produced by the first, like sequencing operators (such as the semicolon) in imperative languages.
     */
    default <T, U> Kind1<Mu, U> thenDiscardFirst(Kind1<Mu, T> ma, Kind1<Mu, U> mb) {
        return sequenceAndDiscardFirst(ma, mb);
    }




}
