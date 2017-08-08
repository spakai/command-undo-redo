package com.spakai.undoredo;

public class CreateGroupAndSubscription implements Command {
    @Override
    public boolean execute() {
        System.out.print("Group and Subscription created");
        return true;
    }
    
    @Override
    public boolean undo() {
        System.out.print("Delete Group and Subscription");
        return true;
    }
    
}
