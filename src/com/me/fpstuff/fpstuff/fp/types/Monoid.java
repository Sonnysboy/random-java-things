package com.me.fpstuff.fpstuff.fp.types;


import com.me.fpstuff.fpstuff.fp.kinds.Kind;

public interface Monoid<Mu> extends Semigroup<Mu> {

    Kind<Mu> mempty();

    default Kind<Mu> mappend(Kind<Mu> a, Kind<Mu> b) {
        return sappend(a, b);
    }
}
