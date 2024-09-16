package com.me.diabolicalideas;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface Functor<A, FA, B, FB> {

    FB fmap(Function<? super A, B> f, FA x);

    static <A, B> Functor<A, Optional<A>, B, Optional<B>> maybe() {
        return (f, x) -> x.map(f);
    }

    static <A, B> Functor<A, List<A>, B, List<B>> list() {
        return (f, x) -> x.stream().map(f).toList();
    }

    static <A, B, FA, FB, C, FC>
    Functor<A, FA, B, FC> iDontKnowWhatToCallIt(Functor<A, FA, B, FB> f,
                                                Functor<B, C, FB, FC> g,
C y) {
        return new Functor<A, FA, B, FC>() {
            @Override
            public FC fmap(Function<? super A, B> f1, FA x1) {
                return g.fmap(y -> f.fmap(f1, x1), y);
            }
        };
    }


}

class Test {
    public static void main(String[] args) {
        System.out.println(Functor.iDontKnowWhatToCallIt(
                Functor.maybe(),
                Functor.list(),
                Arrays.asList(4,5,6,7)).fmap(y -> (int) y * 4, Optional.of(8)));

    }
};
