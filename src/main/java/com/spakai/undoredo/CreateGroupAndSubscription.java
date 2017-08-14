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
    public ResultInfo execute() {
        CreateGroupResponse response = receiver.createGroup(groupId,subscriptionId);
        if ( response.getResultCode() == 0L) {
            
            return new ResultInfo(0L,"");
        }
        
        return new ResultInfo(response.getResultCode(),response.getResultMessage());
    }
    
    @Override
    public ResultInfo undo() {
        DeleteGroupResponse response = receiver.deleteGroup(groupId);
        if ( response.getResultCode() == 0L) {
            
            return new ResultInfo(0L,"");
        }
        
        return new ResultInfo(response.getResultCode(),response.getResultMessage());
    }
}
