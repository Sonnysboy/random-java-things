package com.me.parsercombinator;

public class Tuple<T, U> {

    private final T first;
    private final U second;

    Tuple(T first, U second) {
        this.first  = first;
        this.second = second;
    }
    public T fst() { return first; }
    public U snd () { return second; }




}
