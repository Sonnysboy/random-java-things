package com.me.fpstuff.fpstuff.fp.tests;

import com.me.fpstuff.fpstuff.fp.instances.Lists;
import com.me.fpstuff.fpstuff.fp.instances.MaybeApplicative;
import com.me.fpstuff.fpstuff.fp.instances.Maybes;
import com.me.fpstuff.fpstuff.fp.kinds.ListKind;
import com.me.fpstuff.fpstuff.fp.kinds.Maybe;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicativeTest {
    @Test
    void maybe() {
        final MaybeApplicative app = Maybes.applicative();
        final Maybe<Integer> v = Maybe.widen(42);
        final var z = ((Function<Integer, Integer>) integer -> integer + 2);
//        identity
        {
//        'assertEquals()' between objects of inconvertible types 'Kind1<Mu, Object>' and 'Maybe<Integer>'
//        that's funny
            assertEquals(app.app(app.pure(z), v), v);
        }
    }
    @Test
    void list() {
        final var app = Lists.applicative();

        Function<Integer, Integer> succ = x -> x + 1;
        Function<Integer, Integer> succuc = succ.andThen(succ);
        final ListKind<Integer> xs = ListKind.of(1, 2, 3, 4);
        assertEquals(app.app(ListKind.of(succ, succuc), xs),
                ListKind.of(2, 3, 4, 5, 3, 4, 5, 6));
    }
}
