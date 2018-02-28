package week_2.exercise_1;


/**
 * Java generics. Explain why Java prohibits generic array creation.
 */
public class JavaGenericsAndArray {
    /**
     * Java数组不支持泛型是因为：
     * 1. Java 5.0 引入泛型的时候，在编译时对于泛型的处理使用了一种擦除机制，擦除了不同类的特性。这样JVM装载运行的时候就都是看作Object类型
     * 2. 但是Java数组在取出对象的时候会强制进行类型检查，这样即使允许创建数组时使用泛型，取对象的时候也会报ClassCastException
     * 3. 泛型的引入就是为了解决ClassCastException, 搞成这个样子，只能理解成实现泛型的设计有问题。为了绕过这个问题（赶工期），只好干脆禁止使用泛型数组
     */

    /**
     * It is because.
     * 1. When generics was imported in Java 5.0, the designers used a mechanism calls erasure to make sure different classes' difference was eliminated
     * so the JVM will see them as object for all.
     * 2. But the Java array will do a class cast(type check) on every access. So a ClassCastException will be inevitable.
     * 3. To deal with this issue, they banned generic array for good. Maybe the deadline is near so they have to do this :P
     */
}
