package com.spakai.undoredo;


public class Invoker {
    
    private final CommandStack commandStack;
    
    public Invoker(int undoBufferSize) {
        commandStack = new CommandStack(undoBufferSize);
    }
    
    public void execute(Command cmd) {
        commandStack.push(cmd);
        cmd.execute();
    }
    
    public void undo() {
        commandStack.getLastCommand().undo();
    }
    
    public void redo() {
        
        
    }
}
