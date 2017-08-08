package com.spakai.undoredo;

public class CreateGroupAndSubscription implements Command <SPROutput>{
    @Override
    public SPROutput execute() {
        return new SPROutput(0L,"Group and subscription created");
    }
    
    @Override
    public SPROutput undo() {
        return new SPROutput(0L,"Deleted Group and Subscription");
    }
    
}
