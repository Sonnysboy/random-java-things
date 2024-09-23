package com.me.fpstuff.fpstuff.fp.instances;

import com.me.fpstuff.fpstuff.fp.kinds.Kind1;
import com.me.fpstuff.fpstuff.fp.kinds.ListKind;
import com.me.fpstuff.fpstuff.fp.types.Functor;

import java.util.function.Function;
import java.util.stream.Collectors;

public class ListFunctor implements Functor<ListKind.MU> {
    @Override
    public <T, R> Kind1<ListKind.MU, R> fmap(Function<? super T, ? extends R> f, Kind1<ListKind.MU, T> x) {
        return ListKind.narrow(x).stream().map(f).collect(Collectors.toCollection(ListKind::new));
    }
}
