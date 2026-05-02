package org.example.practice.part4;

public class Service {
    DBStrategy dbStrategy;

    public Service(DBStrategy dbStrategy) {
        this.dbStrategy = dbStrategy;
    }

    void executeQuery( String query){
        dbStrategy.connect();
        dbStrategy.executeQuery(query);
        dbStrategy.close();
    }
}
