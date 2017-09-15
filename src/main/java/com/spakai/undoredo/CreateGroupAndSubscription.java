package com.spakai.undoredo;

public class CreateGroupAndSubscription implements Command<CreateGroupResponse, DeleteGroupResponse>  {

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
    public CreateGroupResponse execute() {
        CreateGroupResponse response = receiver.createGroup(groupId,subscriptionId);
        return response; 
    }
    
    @Override
    public DeleteGroupResponse undo() {
        DeleteGroupResponse response = receiver.deleteGroup(groupId);
        return response;
    }
}
