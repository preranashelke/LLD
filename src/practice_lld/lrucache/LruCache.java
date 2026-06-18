package practice_lld.lrucache;

import java.util.HashMap;
import java.util.Map;

public class LruCache<K,V> {
    private final Map<K, Node<K,V>> cache;
    private final DoubledLinkedList<K,V> doubledLinkedList;
    private final int capacity;

    public LruCache(int capacity){
        this.capacity = capacity;
        cache = new HashMap<>();
        doubledLinkedList = new DoubledLinkedList<>();
    }

    public synchronized V get(K key){
        if(cache.containsKey(key)){
            Node<K,V> node = cache.get(key);
            doubledLinkedList.moveFront(node);
            return node.value;
        }
        return null;
    }

    public synchronized void put(K key, V value){
        if(cache.containsKey(key)){
            Node<K,V> node = cache.get(key);
            node.value=value;
            doubledLinkedList.moveFront(node);
        } else {
            if(cache.size()==capacity){
                Node<K,V> lru = doubledLinkedList.removeLast();
                if (lru!=null)
                    cache.remove(lru.key);
            }
            Node<K,V> node = new Node<>(key, value);
            doubledLinkedList.insertFirst(node);
            cache.put(key, node);
        }
    }


    public synchronized void remove(K key){
        if(!cache.containsKey(key)){
            return;
        }
        Node<K,V> node = cache.get(key);
       doubledLinkedList.remove(node);
       cache.remove(key);
    }

    @Override
    public String toString() {
        return doubledLinkedList.display();
    }


}
