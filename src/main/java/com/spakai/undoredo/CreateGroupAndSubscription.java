package com.spakai.undoredo;

public class CreateGroupAndSubscription implements Command<CreateGroupResponse> {

    // which states do i need to store in order to execute and undo
    private int groupId;
    private int subscriptionId;
    
    // this is the Volt handle apis that talks to VoltDB
    private Receiver receiver;
    
    private VersionKeeper versionKeeper;
    
    public CreateGroupAndSubscription(int groupId, int subscriptionId, Receiver receiver, VersionKeeper versionKeeper) {
        this.groupId = groupId;
        this.subscriptionId = subscriptionId;
        this.receiver = receiver;
        this.versionKeeper = versionKeeper;
    }
    
    @Override
    public CreateGroupResponse execute() {
        CreateGroupResponse response = receiver.createGroup(groupId,subscriptionId);
        if ( response.getResultCode() == 0L) {
            versionKeeper.setGroupVersion(response.getVersionId());
            return new CreateGroupResponse(0L,"");
            
        }
        
        return new CreateGroupResponse(response.getResultCode(),response.getResultMessage());
    }
    
    @Override
    public boolean undo() {
        DeleteGroupResponse response = receiver.deleteGroup(groupId, versionKeeper.getGroupVersion());
        if ( response.getResultCode() == 0L) {
            return true;
        }
        
        return false;
    }
}
