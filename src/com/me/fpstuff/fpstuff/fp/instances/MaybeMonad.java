package com.me.fpstuff.fpstuff.fp.instances;

import com.me.fpstuff.fpstuff.fp.kinds.Kind1;
import com.me.fpstuff.fpstuff.fp.kinds.Maybe;
import com.me.fpstuff.fpstuff.fp.types.Monad;

import java.util.function.Function;

public class MaybeMonad extends MaybeApplicative implements Monad<Maybe.Mu> {
    @Override
    public <T, U> Kind1<Maybe.Mu, U> bind(Kind1<Maybe.Mu, T> k, Function<? super T, ? extends Kind1<Maybe.Mu, U>> f) {
        var ma = Maybe.narrow(k);
        if (!ma.isJust()) return Maybe.nothing();
        return f.apply(ma.unwrap());
    }

    @Override
    public <T, U> Kind1<Maybe.Mu, U> thenDiscardFirst(Kind1<Maybe.Mu, T> ma, Kind1<Maybe.Mu, U> mb) {
        return sequenceAndDiscardFirst(ma, mb);
    }
}
