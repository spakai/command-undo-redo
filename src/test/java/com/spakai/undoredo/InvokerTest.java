package com.spakai.undoredo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class InvokerTest {
    
  private Invoker inv;  
  
  @Before
  public void setup() {
      inv = new Invoker();
  }
  
    class DummyOutput extends Output {
        public DummyOutput(Long resultCode, String resultMessage) {
            super(resultCode, resultMessage);
        }
    }
  
    class CommandTest implements Command {

        private String message;
        
        public CommandTest(String message) {
            this.message = message;
        }
        
        @Override
        public void execute() {
             System.out.println("Execute " + message);
        }
    
        @Override
        public void undo() {
             System.out.println("Undo " + message);
        }
        
         @Override
        public void redo() {
             execute();
        }
    }

  @Test
  public void ExecuteAndUndo() {
      inv.execute(new CommandTest("create group and subscriptions"));
      inv.execute(new CommandTest("create group balances"));
      
      //failed timeout
      inv.redo();
      
      //failed again undo all
      inv.undo(); 
      inv.undo();
      
  }
  


  
  
  
}