package com.me.parsercombinator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
public class ParserTests {


    @Test
    void test() {

    }

    @Test
    void num() {
        Assertions.assertEquals(45, Parsers.num().parse("45").get().fst());
        Assertions.assertEquals(-45, Parsers.num().parse("-45").get().fst());
        Assertions.assertEquals(Optional.empty(), Parsers.num().parse("hello"));
    }
}
