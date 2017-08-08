package com.spakai.undoredo;

public class CreateGroupAndSubscription implements Command {
    @Override
    public void execute() {
        System.out.print("Group and Subscription created");
    }
    
    @Override
    public void undo() {
        System.out.print("Delete Group and Subscription");
    }
    
}
