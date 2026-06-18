package practice_lld.lrucache;

public class LruCacheDemo {

    public static void main(String[] args){
        LruCache<String,Integer> lruCache = new LruCache<>(3);

        lruCache.put("a", 1);
        lruCache.put("b", 2);
        lruCache.put("c", 3);

        System.out.println(lruCache);
        System.out.println(lruCache.get("a"));

        System.out.println(lruCache);
        lruCache.put("d",4);

        System.out.println(lruCache);
        System.out.println(lruCache.get("b"));


    }
}
