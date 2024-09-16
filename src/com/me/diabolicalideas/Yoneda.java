package com.me.diabolicalideas;

import java.util.function.Function;

public interface Yoneda<F,A> {
    // returns an (a -> b) -> F<B>
    <B> Function<Function<? super A, ? extends B>, ? extends F> unyoneda();

    static <F,A,B> Yoneda<F,B> fmap(Function<? super A, ? extends B> f, Yoneda<F,A> y) {
        return new Yoneda<F, B>() {
            @Override
            public <B1> Function<Function<? super B, ? extends B1>, ? extends F> unyoneda() {
                return ab -> y.unyoneda().apply(ab.compose(f));
            }
        };
    }
}
