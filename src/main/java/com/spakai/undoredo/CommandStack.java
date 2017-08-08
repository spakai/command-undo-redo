package com.spakai.undoredo;

import java.util.ArrayList;
import java.util.List;

public class CommandStack {
    private List<Command> commandsStack  = new ArrayList<>();
    private int pointer = 0;
    
    public void push(Command cmd) {
        commandsStack.add(cmd);
        pointer++;
        
    }
    
    public Command getLastCommand() {
        return commandsStack.get(--pointer);
    }
    

    

}