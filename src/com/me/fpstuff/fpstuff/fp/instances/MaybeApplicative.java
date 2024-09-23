package com.me.fpstuff.fpstuff.fp.instances;

import com.me.fpstuff.fpstuff.fp.kinds.Kind1;
import com.me.fpstuff.fpstuff.fp.kinds.Maybe;
import com.me.fpstuff.fpstuff.fp.types.Applicative;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MaybeApplicative extends MaybeFunctor implements Applicative<Maybe.Mu> {
    @Override
    public <T> Kind1<Maybe.Mu, T> pure(T x) {
        if (null == x) throw new NullPointerException(); // todo remove when refactored into Just and Nothing classes.
        return new Maybe<>(x);
    }


    @Override
    public <T, R> Kind1<Maybe.Mu, R> app(Kind1<Maybe.Mu, Function<? super T, ? extends R>> f, Kind1<Maybe.Mu, T> x) {
        if (Maybe.narrow(f).isJust()) return fmap(Maybe.narrow(f).unwrap(), x);
        return Maybe.nothing();
    }

    @Override
    public <A, B, C> Kind1<Maybe.Mu, C> liftA2(BiFunction<? super A, ? super B, ? extends C> f, Kind1<Maybe.Mu, A> x, Kind1<Maybe.Mu, B> y) {
        var nx = Maybe.narrow(x);
        var ny = Maybe.narrow(y);
        if (!(nx.isJust() && ny.isJust())) return Maybe.nothing();
        return Maybe.widen(f.apply(nx.unwrap(), ny.unwrap()));
    }
    @Override
    public <A, B> Kind1<Maybe.Mu, B> sequenceAndDiscardFirst(Kind1<Maybe.Mu, A> a1, Kind1<Maybe.Mu, B> a2) {
        if (!Maybe.narrow(a2).isJust()) return Maybe.nothing();
        return a2;
    }



}
