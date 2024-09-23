package com.me.fpstuff.fpstuff.fp.instances;


import com.me.fpstuff.fpstuff.fp.kinds.Kind1;
import com.me.fpstuff.fpstuff.fp.kinds.ListKind;
import com.me.fpstuff.fpstuff.fp.types.Applicative;

import java.util.function.Function;
import java.util.stream.Collectors;

public class ListApplicative extends ListFunctor implements Applicative<ListKind.MU> {

    @Override
    public <T> Kind1<ListKind.MU, T> pure(T x) {
        return ListKind.of(x);
    }
    @Override
    public <T, R> Kind1<ListKind.MU, R> app(Kind1<ListKind.MU,
            Function<? super T, ? extends R>> f,
                                            Kind1<ListKind.MU, T> x) {
            return ListKind.widen(
                    ListKind.narrow(f).stream().flatMap(f_ ->
                           ListKind.narrow(x).stream().map(f_)).collect(Collectors.toUnmodifiableList()));
    }
}
