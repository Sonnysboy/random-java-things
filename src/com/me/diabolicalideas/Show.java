package com.me.diabolicalideas;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Forced toString, like haskell's deriving Show.
 */
public class Show {


    static final Collector<CharSequence, ?, String> ARRAY_COLLECTOR = Collectors.joining(",", "[", "]");

//    we'll need to build it up.
    private static final Map<Class<?>, Function<Object, String>> show = new HashMap<>() {
    {
        put(int.class, obj -> String.valueOf((int) obj));
        put(double.class, obj -> String.valueOf((double)obj));
        put(float.class, obj -> String.valueOf((float)obj));
        put(long.class, obj -> String.valueOf((long)obj));
        put(short.class, obj -> String.valueOf((short)obj));
        put(byte.class, obj -> String.valueOf((byte)obj));
        put(Integer.class, obj -> String.valueOf(((Integer) obj).intValue()));
        put(String.class, obj -> escape((String) obj));
        put(Double.class, obj -> String.valueOf(((Double) obj).doubleValue()));
        put(Float.class, obj -> String.valueOf(((Float) obj).floatValue()));
        put(Long.class, obj -> String.valueOf(((Long) obj).longValue()));
        put(Byte.class, obj -> String.valueOf(((Byte) obj).byteValue()));
        put(Short.class, obj -> String.valueOf(((Short) obj).shortValue()));
        put(Integer[].class, obj -> Arrays.stream((Integer[]) obj).map(Show::show).collect(ARRAY_COLLECTOR));
        put(String[].class, obj -> Arrays.stream((String[]) obj).map(Show::escape).collect(ARRAY_COLLECTOR));
        put(Double[].class, obj -> Arrays.stream((Double[]) obj).map(Show::show).collect(ARRAY_COLLECTOR));
        put(Float[].class, obj -> Arrays.stream((Float[]) obj).map(Show::show).collect(ARRAY_COLLECTOR));
        put(Long[].class, obj -> Arrays.stream((Long[]) obj).map(Show::show).collect(ARRAY_COLLECTOR));
        put(Byte[].class, obj -> Arrays.stream((Byte[]) obj).map(Show::show).collect(ARRAY_COLLECTOR));
        put(Short[].class, obj -> Arrays.stream((Short[]) obj).map(Show::show).collect(ARRAY_COLLECTOR));
        put(int[].class, obj -> Arrays.stream((int[]) obj).boxed().map(Show::show).collect(ARRAY_COLLECTOR));
        put(double[].class, obj -> Arrays.stream((double[]) obj).boxed().map(Show::show).collect(ARRAY_COLLECTOR));
        put(float[].class, obj -> floatArray((float[]) obj));
        put(long[].class, obj -> longArray((long[]) obj));
        put(byte[].class, obj -> byteArray((byte[]) obj));
        put(short[].class, obj -> shortArray((short[]) obj));
    }
};
    private static String floatArray(float[] gah) {
        String s = "[";
        for (float d: gah) s += d + ",";
        return s.substring(0, s.length() - 1) + "]";
    }
    private static String longArray(long[] gah) {
        String s = "[";
        for (long d: gah) s += d + ",";
        return s.substring(0, s.length() - 1) + "]";
    }
    private static String byteArray(byte[] gah) {
        String s = "[";
        for (byte d: gah) s += d + ",";
        return s.substring(0, s.length() - 1) + "]";
    }
    private static String shortArray(short[] gah) {
        String s = "[";
        for (short d: gah) s += d + ",";
        return s.substring(0, s.length() - 1) + "]";
    }
    private static <T> String showCollection(Collection<T> ts) {
        return ts.stream().map(Show::show).collect(Collectors.joining(",", "[", "]"));

    }
    /** Derive a toString for an object. Useful? probably not. Cool? yeah definitely.
     *
     * @param object The object.
     * @return A derived string representation of <code>object</code>
     */
    public static String show(Object object) {
        if (object == null) return "null";
        if (show.containsKey(object.getClass())) {
            return show.get(object.getClass()).apply(object);
        }
        if (object instanceof Collection<?> f) {
            return showCollection(f);
        }
//        if (!object.toString().equals(object.getClass().getName() + "@" + Integer.toHexString(object.hashCode()))) {
//            return object.toString(); // if it's not the default, return that one.
//        } GAH! commented out for testing.

        show.put(object.getClass(), k -> {

            final StringBuilder builder = new StringBuilder();
            final String className = object.getClass().getSimpleName();
            builder.append(className);
            builder.append(" {");
            for (Field f: object.getClass().getDeclaredFields()) {
                if (f.canAccess(object)) {
                    try {
                        builder.append(f.getName()).append("=").append(show(f.get(k))).append(",");
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            builder.deleteCharAt(builder.length()-1);
            builder.append("}");
            return builder.toString();
        });
        return show.get(object.getClass()).apply(object);
    }

    private static String escape(String string) {
        return "\"" + string.replaceAll("\n", "\\\\n").replaceAll("\t", "\\\\t") + "\"";
    }

    public static void main() {
        System.out.println(show(new int[] { 1,2,3,4,5,6,7,8,9,10 }));
        System.out.println(show(new long[] { 1,2,3,4,5,6,7,8,9,10 }));
        System.out.println(show(new float[] { 1,2,3,4,5,6,7,8,9,10 }));
        System.out.println(show(new double[] { 1,2,3,4,5,6,7,8,9,10 }));
        System.out.println(show(new short[] { 1,2,3,4,5,6,7,8,9,10 }));
        System.out.println(show(new byte[] { 1,2,3,4,5,6,7,8,9,10 }));

        Book book1 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, "Fiction");
        Book book2 = new Book("1984", "George Orwell", 1949, "Dystopian");
        Book book3 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "Classic");
        Book book4 = new Book("Pride and Prejudice", "Jane Austen", 1813, "Romance");
        Book book5 = new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "Fiction");
        System.out.println(show(book1));
        System.out.println(show(book2));
        System.out.println(show(book3));
        System.out.println(show(book4));
        System.out.println(show(book5));

        final var l = new List("forty", new List("fifty", new List("sixty", null)));
        System.out.println(show(l));
    }
//    random data i forced chatgpt to make.

    public record Book(String title, String author, int publicationYear, String genre) {}

    private record List(Object data, List next){};

}
