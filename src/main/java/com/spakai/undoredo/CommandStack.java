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
        commandsStack.add(cmd);
        pointer++;
        
        if(commandsStack.size() > stackSize) {
            commandsStack.remove(0);
            pointer--;
        }
        
    }
    
    public Command getLastCommand() {
        return commandsStack.get(--pointer);
    }
    
     public int getSize() {
        return commandsStack.size();
    }

    

}