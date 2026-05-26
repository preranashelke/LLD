package design_patterns;

/***
 * Singleton Pattern ensures that a class has only one instance and provides a global access point to it.
 * The constructor is private so no one can create objects using new.
 * The instance is created lazily using double-checked locking to ensure thread safety with minimal synchronization overhead.
 * The volatile keyword Prevents partially constructed object visibility due to instruction reordering
 * ex: configuration, cache, logging
 */
public class SingletonPattern {
    private static volatile SingletonPattern instance;

    private SingletonPattern(){};

    public static SingletonPattern getInstance(){
        if(instance == null){
            synchronized (SingletonPattern.class){
                if(instance == null){
                    instance = new SingletonPattern();
                }
            }
        }
        return instance;
    }
}

/***
 * Enum Singleton is thread-safe by default and handles serialization and reflection issues automatically
 */

enum Singleton{
    INSTANCE;

    public String getValue(String key){
        return "value";
    }
}
