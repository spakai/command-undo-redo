package com.spakai.undoredo;

public interface Receiver {
    public void createGroup(int groupId, int subscriptionId);
    public void deleteGroup(int groupId);        
    //many more
}