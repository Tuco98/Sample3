package org.example.practice.part11;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SubscriptionManager {
    private Map<String, Map<EventType, Set<Channel>>> subscriptions = new ConcurrentHashMap<>();

    public void subscribe(String stakeHolderId, EventType eventType, Set<Channel> channels){
        subscriptions.computeIfAbsent(stakeHolderId, k-> new ConcurrentHashMap<>());
        subscriptions.get(stakeHolderId).computeIfAbsent(eventType, eventType1 -> new HashSet<>());
        subscriptions.get(stakeHolderId).get(eventType).addAll(channels);
    }

    public void unsubscribe(String stakeHolderId, EventType eventType){
        if(subscriptions.containsKey(stakeHolderId)){
            subscriptions.get(stakeHolderId).remove(eventType);
        }
    }

    public void addChannel(String stakeHolderId, EventType eventType, Channel channel){
        if(subscriptions.containsKey(stakeHolderId) && subscriptions.get(stakeHolderId).containsKey(eventType)){
            subscriptions.get(stakeHolderId).get(eventType).add(channel);
        }
    }

    public void removeChannel(String stakeHolderId, EventType eventType, Channel channel){
        if(subscriptions.containsKey(stakeHolderId) && subscriptions.get(stakeHolderId).containsKey(eventType)){
            subscriptions.get(stakeHolderId).get(eventType).remove(channel);
        }
    }

    public Set<Channel> getSubscribedChannels(String stakeHolderId, EventType eventType){
        if(subscriptions.containsKey(stakeHolderId) && subscriptions.get(stakeHolderId).containsKey(eventType)){
            return subscriptions.get(stakeHolderId).get(eventType);
        }
        return Collections.emptySet();
    }
}
