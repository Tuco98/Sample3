package org.example.practice.Part6;

import java.util.HashMap;
import java.util.Map;

public class ChannelFactory {
    public static Map<Channel,NotificationChannel> map = new HashMap<>();

    public NotificationChannel getChannel(Channel channel){
        return map.get(channel);
    }
}
