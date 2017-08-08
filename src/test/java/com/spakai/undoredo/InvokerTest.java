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
  
    class CommandTest implements Command<DummyOutput> {

        private String message;
        
        public CommandTest(String message) {
            this.message = message;
        }
        
        @Override
        public DummyOutput execute() {
             System.out.println("Execute " + message);
             return new DummyOutput(0L,"Executed Dummy");
            
        }
    
        @Override
        public DummyOutput undo() {
             System.out.println("Undo " + message);
             return new DummyOutput(0L,"Undo Dummy");
            
        }
    }

  @Test
  public void ExecuteAndUndo() {
      inv.execute(new CommandTest("create group and subscriptions"));
      inv.execute(new CommandTest("create group balances"));
      inv.redo();
      inv.undo();
      inv.undo();
      
  }
  


  
  
  
}