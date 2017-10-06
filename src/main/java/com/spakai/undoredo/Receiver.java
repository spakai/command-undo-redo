package com.spakai.undoredo;

public interface Receiver {
    public CreateGroupResponse createGroup(int groupId, int subscriptionId);
    public DeleteGroupResponse deleteGroup(int groupId, String versionId);        
    //many more
}