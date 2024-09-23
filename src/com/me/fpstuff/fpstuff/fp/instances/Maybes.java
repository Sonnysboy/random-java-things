package com.me.fpstuff.fpstuff.fp.instances;

public class Maybes {
    private static final MaybeApplicative app = new MaybeApplicative();
    private static final MaybeFunctor functor = new MaybeFunctor();


    public static MaybeApplicative applicative() {
        return app;
    }

    public static MaybeFunctor functor() {
        return functor;
    }
}
