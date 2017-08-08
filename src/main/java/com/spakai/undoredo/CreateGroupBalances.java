package com.spakai.undoredo;

public class CreateGroupBalances implements Command {
    @Override
    public boolean execute() {
        System.out.print("Group Balances created");
        return true;
    }
    
    @Override
    public boolean undo() {
        System.out.print("Group Balances deleted");
        return true;
    }
}
