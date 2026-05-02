package org.example.inMemoryKV.store;

import org.example.inMemoryKV.model.Entry;
import org.example.inMemoryKV.model.ExpiryNode;

import java.util.Comparator;
import java.util.concurrent.*;

public class InMemoryKVStore <K, V> implements KeyValueStore<K,V>{
    private final ConcurrentHashMap<K, Entry<V>> store;
    private final ScheduledExecutorService cleaner;
    private final PriorityBlockingQueue<ExpiryNode<K>> expiryHeap;

    public InMemoryKVStore() {
        this.cleaner = Executors.newSingleThreadScheduledExecutor();
        this.store = new ConcurrentHashMap<>();
        this.expiryHeap = new PriorityBlockingQueue<>(11, Comparator.comparingLong(ExpiryNode::getExpiryTime));
        startCleanupTask();
    }

    @Override
    public void put(K key, V value, long ttlMillis) {
        store.put(key,new Entry<>(value, ttlMillis));
        long expTime = ttlMillis>0?System.currentTimeMillis()+ttlMillis:Long.MAX_VALUE;
        expiryHeap.offer(new ExpiryNode<>(key,expTime));
    }

    @Override
    public V get(K key) {
        Entry<V> obj = store.get(key);

        if(obj == null) return null;

        if(obj.isExpired()){
            store.remove(key);
            return null;
        }

        return obj.getValue();

    }

    @Override
    public void delete(K key) {
        store.remove(key);
    }

    @Override
    public boolean containsKey(K key) {
        return store.containsKey(key);
    }

    @Override
    public int size() {
        return store.size();
    }

    private void startCleanupTask(){
        cleaner.scheduleAtFixedRate(()->{
            long now = System.currentTimeMillis();
            while (true){
                ExpiryNode<K> node = expiryHeap.peek();
                if(node == null || node.getExpiryTime() > now){
                    break;
                }

                expiryHeap.poll();
                Entry<V> obj = store.get(node.getKey());
                if(obj != null && obj.getExpiryTime() == node.getExpiryTime()){
                    store.remove(node.getKey());
                }
            }
        },1,1, TimeUnit.HOURS);
    }
}
