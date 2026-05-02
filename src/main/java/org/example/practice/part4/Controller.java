package org.example.practice.part4;

public class Controller {
    DBStrategyFactory dbStrategyFactory;
    Service service;

    void executeQuery(String type, String query){
        DBStrategy dbStrategy = dbStrategyFactory.getDBStrategy(type);
        service = new Service(dbStrategy);
        service.executeQuery(query);
    }
}
