package org.example.notificationSystem.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelFactory {
    private final Map<ChannelType, NotificationChannel> channels;

    public ChannelFactory(List<NotificationChannel> channelList){
        channels = new HashMap<>();
        for(NotificationChannel channel: channelList){
            channels.put(channel.getChannelType(),channel);
        }
    }

    public NotificationChannel getChannel(ChannelType channelType){
        return channels.get(channelType);
    }

}
