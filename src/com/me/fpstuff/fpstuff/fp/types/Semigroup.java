package com.me.fpstuff.fpstuff.fp.types;


import com.me.fpstuff.fpstuff.fp.kinds.Kind;

public interface Semigroup<Mu> {

    Kind<Mu> sappend(Kind<Mu> a, Kind<Mu> b);
}
