package org.example.splitwise;

public class SplitFactory {
    static SplitStrategy getStrategy(SplitType splitType){
        if(splitType == SplitType.EXACT) return new ExactStrategy();
        else if(splitType == SplitType.EQUAL) return new EqualStrategy();
        else return new PercentageStrategy();
    }
}
