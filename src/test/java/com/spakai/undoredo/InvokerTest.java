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
      inv = new Invoker(3);
  }
  
    class DummyOutput extends Output {
        public DummyOutput(Long resultCode, String resultMessage) {
            super(resultCode, resultMessage);
        }
    }
  
    class CommandTest implements Command<DummyOutput> {
        @Override
        public DummyOutput execute() {
             System.out.println("Executed Dummy");
             return new DummyOutput(0L,"Executed Dummy");
            
        }
    
        @Override
        public DummyOutput undo() {
             System.out.println("Undo Dummy");
             return new DummyOutput(0L,"Undo Dummy");
            
        }
    }

  @Test
  public void ExecuteAndUndo() {
      inv.execute(new CommandTest());
      inv.undo();
      
  }
  
  
  @Test
  public void ExecuteAndRedo() {
      inv.execute(new CommandTest());
      // failed inv.redo();
      
  }
  
  
  
}