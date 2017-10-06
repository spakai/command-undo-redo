package com.spakai.undoredo;

import java.util.Stack;

public class Invoker {
    
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;
    
    public Invoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }
    
    /**
    * Executes a command.
    * If the command is successful , push the command into undoStack
    * so that the action can be undo-ed.
    * If a command failed to execute , add it to the redo stack 
    * so that the action can be redo-ed.
    * Redo stack is cleared every time a command is successfully executed 
    * since there is no reason to redo a successful command or the commands before it.
    *
    * @param  cmd  Command object
    * @return      ResultInfo object
    */
    
    public void execute(Command cmd) {
        Response ri = cmd.execute();
        if (ri.getResultCode() == 0L) {
            undoStack.push(cmd);
            redoStack.clear();
        } else {
            redoStack.add(cmd);
        }
    }
    
     /**
    * Undo a command that was previously successfully executed
    *
    * @return      ResultInfo object,null if nothing to run
    */
    public boolean undo() {
        
        if (isUndoAvailable()) {
            Command cmd = undoStack.pop();
            return cmd.undo();            
            
        }
        
        return false;
    }
    
    /**
    * Executes a command taken from the redo stack.
    * Runs the execute logic
    * @return      ResultInfo object, null if nothing to run
    */
    
    public void redo() {
        if (isRedoAvailable()) {
            Command cmd = redoStack.pop();
            execute(cmd);
        }
    }
        
    /**
    * Clears the undo history
    */
    
    public void clearUndoHistory() {
        undoStack.clear();        
    }
   
    /**
    * Clears the redo history
    */
    public void clearRedoHistory() {
        redoStack.clear();
    }    
        
    /**
    * Check if there is command available to undo
    * @return      true if available , false otherwise
    */
    public boolean isUndoAvailable() {
        return !undoStack.empty();
    }
    
    /**
    * Check if there is command available to redo
    * @return      true if available , false otherwise
    */
    
    public boolean isRedoAvailable() {
        return !redoStack.empty();
    }
    
}
