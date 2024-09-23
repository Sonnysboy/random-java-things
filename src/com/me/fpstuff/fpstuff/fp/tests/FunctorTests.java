package com.me.fpstuff.fpstuff.fp.tests;


import com.me.fpstuff.fpstuff.fp.instances.ListFunctor;
import com.me.fpstuff.fpstuff.fp.instances.Maybes;
import com.me.fpstuff.fpstuff.fp.kinds.Maybe;
import com.me.fpstuff.fpstuff.fp.other.Prelude;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class FunctorTests {


  @Test
  void identity() {
    Maybe<Integer> a = Maybe.widen(4);
    final var func = Maybes.functor();
    Assertions.assertEquals(func.fmap(Prelude::id, a), a);
    final Function<Integer, Integer> f = x -> x + 5;
    final Function<Integer, Integer> g = x -> x + 10;
    Assertions.assertEquals(func.fmap(x -> f.andThen(g).apply(x), a), func.fmap(
        f.andThen(g), a));
  }

  @Test
  public void testFunctor() {
    List<Integer> xs = IntStream.range(0, 40).boxed().toList();
    ListFunctor f = new ListFunctor();

  }


}
