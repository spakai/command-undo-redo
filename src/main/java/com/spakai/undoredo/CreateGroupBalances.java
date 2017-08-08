package com.spakai.undoredo;

public class CreateGroupBalances implements Command <SPROutput>{
    @Override
    public SPROutput execute() {
        return new SPROutput(0L,"Group balances created");
    }
    
    @Override
    public SPROutput undo() {
        return new SPROutput(0L,"Deleted Group balances");
    }
    
}
