package org.example.notificationSystem.model;

import java.util.Map;

public class UserPreference {
    private String userId;
    private Map<ChannelType, Boolean> channelPreferences;

    public boolean isEnabled(ChannelType channelType){
        return channelPreferences.getOrDefault(channelType,false);
    }
    public UserPreference(String userId, Map<ChannelType, Boolean> channelPreferences) {
        this.userId = userId;
        this.channelPreferences = channelPreferences;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<ChannelType, Boolean> getChannelPreferences() {
        return channelPreferences;
    }

    public void setChannelPreferences(Map<ChannelType, Boolean> channelPreferences) {
        this.channelPreferences = channelPreferences;
    }


}
