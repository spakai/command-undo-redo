package com.spakai.undoredo;

public class CreateGroupBalances implements Command {
    @Override
    public void execute() {
        System.out.print("Group Balances created");
    }
    
    @Override
    public void undo() {
        System.out.print("Group Balances deleted");
}
