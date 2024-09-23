package com.me.fpstuff.fpstuff.fp.instances;

import com.me.fpstuff.fpstuff.fp.kinds.Kind1;
import com.me.fpstuff.fpstuff.fp.kinds.Maybe;
import com.me.fpstuff.fpstuff.fp.types.Functor;

import java.util.function.Function;

public class MaybeFunctor implements Functor<Maybe.Mu> {
    @Override
    public <T, R> Kind1<Maybe.Mu, R> fmap(Function<? super T, ? extends R> f, Kind1<Maybe.Mu, T> x) {
        if (!Maybe.isJust(Maybe.narrow(x))) return Maybe.nothing();
        return Maybe.widen(f.apply(Maybe.narrow(x).unwrap()));
    }
}
