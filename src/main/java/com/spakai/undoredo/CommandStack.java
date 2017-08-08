package com.spakai.undoredo;

import java.util.ArrayList;
import java.util.List;

public class CommandStack {
    private final int stackSize;
    private List<Command> commandsStack;  
    private int pointer = 0;
    
    public CommandStack(int stackSize) {
        this.stackSize = stackSize;
        commandsStack = new ArrayList<>(stackSize);
    }
    
    public void push(Command cmd) {
        
        if(commandsStack.size() +1 > stackSize) {
            commandsStack.remove(0);
            pointer--;
        }
        
        commandsStack.add(cmd);
        pointer++;
    }
    
    public Command getLastCommand() {
        if ( pointer > 0) {
            return commandsStack.get(--pointer);
        }
        
        return null;
    }
    
     public int getSize() {
        return commandsStack.size();
    }

    

}