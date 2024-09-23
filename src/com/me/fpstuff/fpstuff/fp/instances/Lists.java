package com.me.fpstuff.fpstuff.fp.instances;

public final class Lists {
    private static final ListFunctor functor;
    private static final ListApplicative applicative;


    public static ListFunctor functor() {
        return functor;
    }
    public static ListApplicative applicative() {
        return applicative;
    }

    static {
        functor = new ListFunctor();
        applicative = new ListApplicative();
    }
}
