package com.me.parsercombinator;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class Parsers {

    public static Parser<Character> sat(Predicate<Character> p) {
        return x -> {
            if (x.isEmpty()) return Optional.empty();
            var test = x.charAt(0);
            if (p.test(test)) return Optional.of(
                    new Tuple<>(test, x.substring(1)));
            return Optional.empty();
        };
    }

    public static Parser<Character> character(char what) {
        return sat(x -> x == what);
    }

    public static Parser<Character> next() {
        return sat(x -> true);
    }

    public static Parser<String> full() {
        return Parser.many(next()).fmap(Parsers::realize);
    }

    public static Parser<String> seek(char what) {
        return Parser.many(sat(x -> x != what)).fmap(Parsers::realize);
    }

    public static Parser<Character> either(char x, char y) {
        return character(x).or(character(y));
    }

    /**
     * num :: Parser Integer
     * num = maybe id (const negate) <$> optional (char '-') <*> (toInteger <$> some digit)
     * where
     * toInteger = Data.Foldable.foldl' ((+) . (* 10)) 0
     * <p>
     * num = (maybe id (const negate) <$> optional (char '-')) <*> (toInteger <$> some digit)
     * = fmap (maybe id (const negate)) (optional (char '-')) <*> (toInteger <$> some digit)
     * = (fmap (maybe id (const negate)) (optional (char '-'))) <*> (toInteger <$> some digit)
     * |
     * V
     * (fmap (maybe id (const negate)) (optional (char '-'))) :: Num a => Parser (a -> a)
     * | to our notation
     * V
     * (optional (char '-')).fmap(maybe id (const negate)).app(part2)
     * <p>
     * =
     *
     */
    public static Parser<Integer> num() {
        return Parser.app(
                Parser.optional(character('-'))
                        .fmap(z -> FPHelper.maybe(Function.identity(), (a -> (b -> (-1 * a))), z)),
                y -> (Parser.some(digit()).fmap(Parsers::toInteger).parse(y)));
    }

    private static int toInteger(List<Integer> digits) {
        return digits.stream().reduce(0, (x, acc) -> acc + (10 * x));

    }

    public static Parser<Integer> digit() {
        return x -> {
            if (x.isEmpty()) return Optional.empty();
            final var c = x.charAt(0);
            final var cs = x.substring(1);
            if (Character.isDigit(c)) {
                return Optional.of(new Tuple<>(digitToInt(c), cs));
            }
            return Optional.empty();
        };
    }

    private static int digitToInt(char c) {
        return c - '0';
    }

    private static String realize(List<Character> what) {
        var sb = new StringBuilder();
        what.forEach(sb::append);
        return sb.toString();
    }
}
