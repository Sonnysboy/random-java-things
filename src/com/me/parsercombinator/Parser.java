package com.me.parsercombinator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface Parser<T> extends Function<String, Optional<
        Tuple<T, String>>> {

    default Optional<Tuple<T, String>> parse(String s) {
        return apply(s);
    };

    // === functor

    // Parser $ fmap (first transform) . parse p
    default <U> Parser<U> fmap(Function<? super T, ? extends U> f) {
        return (x) -> parse(x).map(r -> new Tuple<>(f.apply(r.fst()), r.snd()));
    }

    /**
     * <$
     */
    default <U> Parser<U> replaceAll(U what) {
        return fmap(x -> what);
    }
    // ===

    // === APPLICATIVE
    // Parser $ \x -> Just (a,x)
    static <U> Parser<U> pure(U u) {
        return x -> Optional.of(new Tuple<>(u, x));
    }

    //   Parser f <*> Parser g = Parser $ \x -> f x >>= \(r, s') -> g s' >>= \(r', s'') -> Just (r r', s'')
    default <U> Parser<U> app(Parser<Function<T, U>> g) {
        return x -> parse(x).flatMap(t ->
                g.parse(t.snd()).flatMap(t2 ->
                        Optional.of(new Tuple<>(t2.fst().apply(t.fst()), t2.snd()))));

    }
    static <T,U> Parser<U> app(Parser<Function<T, U>> f, Parser<T> g) {
        return x -> f.parse(x).flatMap(y -> {
            var r = y.fst();
            var sprime = y.snd();
            return g.parse(sprime).map(z -> new Tuple<>(r.apply(z.fst()), z.snd()));
        });

    }
    // ===
    // === monad
    default <U> Parser<U> bind(Function<? super T, ? extends Parser<U>> g) {
        return x -> parse(x).flatMap(y -> g.apply(y.fst()).parse(y.snd()));
    }

    /**
     * *>
     */
    default <U> Parser<U> sequenceThenDiscardFirst(Parser<U> a2) {
        return a2.app(replaceAll(x -> x));
    }

    /**
     * <*
     */
    default <U> Parser<T> sequenceThenDiscardSecond(Parser<U> a2) {
        return liftA2((a,t) -> a, this, a2);
    }
    // ===

    // === ALTERNATIVE
    // Parser $ const Nothing
    default <U> Parser<U> empty() {
        return x -> Optional.empty();
    }

    //  Parser p <|> Parser r = Parser $ \x -> p x <|> r x
    default Parser<T> or(Parser<T> r) {
        return x -> parse(x).or(() -> r.parse(x));
    }

//    liftA2 f x y = f <$> x <*> y
    static <T, U, V> Parser<V> liftA2(BiFunction<? super T, ? super U, ? extends V> f, Parser<T> x, Parser<U> y) {
        return y.app(x.fmap(FPHelper.curry(f)));
    }

    static <T> Parser<Optional<T>> optional(Parser<T> what) {
        return what.fmap(Optional::of).or(pure(Optional.empty()));
    }

    // 1 or more
    static <T> Parser<List<T>> some(Parser<T> p) {
        return x -> {
            final var acc = new ArrayList<T>();
            while (true) {
                Optional<Tuple<T, String>> add = p.parse(x);
                if (add.isEmpty()) {
                    if (acc.isEmpty()) return Optional.empty(); // requires at least 1, otherwise fails entirely.
                    return Optional.of(new Tuple<>(acc, x));
                }
                x = add.get().snd();
                acc.add(add.get().fst());
            }
        };
    }
//    0 or more
    static <T> Parser<List<T>> many(Parser<T> p) {
        return x -> {
            final var acc = new ArrayList<T>();
            while (true) {
                Optional<Tuple<T, String>> add = p.parse(x);
                if (add.isEmpty()) {
                    return Optional.of(new Tuple<>(acc, x));
                }
                x = add.get().snd();
                acc.add(add.get().fst());
            }
        };
    }
    // ===

}
