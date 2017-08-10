package com.spakai.undoredo;

public class CreateGroupAndSubscription implements Command {

    // which states do i need to store in order to execute and undo
    private int groupId;
    private int subscriptionId;
    
    // this is the Volt handle apis that talks to VoltDB
    private Receiver receiver;
    
    public CreateGroupAndSubscription(int groupId, int subscriptionId, Receiver receiver) {
        this.groupId = groupId;
        this.subscriptionId = subscriptionId;
        this.receiver = receiver;
    }
    
    @Override
    public void execute() {
        receiver.createGroup(groupId,subscriptionId);
    }
    
    @Override
    public void undo() {
        receiver.deleteGroup(groupId);
    }
    
    @Override
    public void redo() {
        execute();     
    }
}
