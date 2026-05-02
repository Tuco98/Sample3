package org.example.practice.part8;

public class SplitStrategyFactory {
    public SplitStrategy getStrategyImpl(SplitType splitType){
        if(splitType.equals(SplitType.EQUAL)) return new EqualStrategy();
        else if(splitType.equals(SplitType.EXACT)) return new ExactStrategy();
        else return new PercentageStrategy();
    }
}
