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
    
    public ResultInfo execute(Command cmd) {
        ResultInfo ri = cmd.execute();
        if (ri.getResultCode() == 0L) {
            undoStack.push(cmd);
            redoStack.clear();
        } else {
            redoStack.add(cmd);
        }
        
        return ri;
    }
    
     /**
    * Undo a command that was previously successfully executed
    *
    * @return      ResultInfo object,null if nothing to run
    */
    public ResultInfo undo() {
        
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            ResultInfo ri = cmd.undo();            
            return ri;
        }
        
        return null;
        
    }
    
    /**
    * Executes a command taken from the redo stack.
    * Runs the execute logic
    * @return      ResultInfo object, null if nothing to run
    */
    
    public ResultInfo redo() {
        if (!redoStack.empty()) {
            Command cmd = redoStack.pop();
            return execute(cmd);
        }
        
        return null;
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
    
    
}
