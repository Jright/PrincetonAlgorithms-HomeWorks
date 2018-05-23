package week_6;

import java.util.HashMap;

public class HashingWithWrongAPI {

//    第 2 个问题
//    Hashing with wrong hashCode() or equals(). Suppose that you implement a data type OlympicAthlete for use in a java.util.HashMap.
//
//    Describe what happens if you override hashCode() but not equals().
//    Describe what happens if you override equals() but not hashCode().
//    Describe what happens if you override hashCode() but implement public boolean equals(OlympicAthlete that) instead of public boolean equals(Object that).

    //1.Without equals function, There will be a difficulty to locate an object, if the object is stored in a same 'bucket' with other objects, because
    //when a 'bucket' in a hash-table has stored more than one object, then we need equals function to check which one object is what we looking for(contains function)

    //2.The map will function very ineffectively as it won't be smart enough to make a proper hashmap whose values(objects) are not distributed evenly.

    //3. There will be exceptions when compare OlympicAthlete with other types of objects which inherited equals function.

    //======================================================
    //Copied from https://blog.csdn.net/jason_cuijiahui/article/details/78136358
//    Override hashCode() but not equals()
//    a) 对于void put(Key key, Value val)来说，对于引用不同但本质上是equal的key1, key2，会哈希到同一个bucket中，但两个都会保存下来，因为认为他们不是equal的。
    //TODO why??
//    b) 对于Value get(Key key)来说，对于引用不同但本质上是equal的key3，虽然会哈希到同一个bucket，但返回的是null。
//
//    Override equals() but not hashCode()
//    a) 对于void put(Key key, Value val)来说，对于equal的key1, key2，会哈希到不同的bucket中，因为hashCode()的默认实现是对象的地址。
//    b) 对于Value get(Key key)来说，对于equal的key3，会哈希到不同的bucket，返回的是null。
//
    //TODO
//    Override hashCode() but implement public boolean equals(OlympicAthlete that) instead of public boolean equals(Object that)
//    其实就跟情况1一样[Override hashCode() but not equals()]，因为x.key是Object类型，所以key.equals(x.key)中只会调用默认equals(Object that)，而不会调用equals(OlympicAthlete that)。
//    注意：默认的类型转换只发生在子类->父类
    public static void main(String[] args){
        HashMap<String, String> map = new HashMap<>();
    }

}
