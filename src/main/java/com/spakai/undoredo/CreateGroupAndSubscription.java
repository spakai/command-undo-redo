package com.spakai.undoredo;

public class CreateGroupAndSubscription implements Command {

    // which states do i need to store in order to execute and undo
    private int groupId;
    private int subscriptionId;
    
    // this is the Volt handle apis that talks to VoltDB
    private Receiver receiver;
    
    private final VersionKeeper versionKeeper;
    
    public CreateGroupAndSubscription(int groupId, int subscriptionId, Receiver receiver, VersionKeeper versionKeeper) {
        this.groupId = groupId;
        this.subscriptionId = subscriptionId;
        this.receiver = receiver;
        this.versionKeeper = versionKeeper;
        
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
        
        DeleteGroupResponse response = receiver.deleteGroup(groupId, versionKeeper.getVersionId());
        if ( response.getResultCode() == 0L) {
            
            return new ResultInfo(0L,"");
        }
        
        return new ResultInfo(response.getResultCode(),response.getResultMessage());
    }
}
