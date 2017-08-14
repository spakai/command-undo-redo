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
  
    class DummyOutput extends Response {
        public DummyOutput(Long resultCode, String resultMessage) {
            super(resultCode, resultMessage);
        }
    }
  
    class CommandTest implements Command {

        private String message;
        private boolean rt_e,rt_u,rt_r;
        
        
        public CommandTest(String message) {
            this.message = message;
            this.rt_e = true;
            this.rt_r = true;
            this.rt_u = true;
        
        }
        
        public CommandTest(String message,boolean rt_e, boolean rt_u, boolean rt_r) {
            this.message = message;
            this.rt_e = rt_e;
            this.rt_r = rt_r;
            this.rt_u = rt_u;
        }
        
        @Override
        public boolean execute() {
             System.out.println("Execute " + message);
             return rt_e;
        }
    
        @Override
        public boolean undo() {
             System.out.println("Undo " + message);
             return rt_e;
        }
        
         @Override
        public boolean redo() {
             execute();
             return rt_r;
        }
    }

  @Test
  public void NothingToUndo() {
   inv.execute(new CommandTest("create group and subscriptions"));
   inv.execute(new CommandTest("create group balances"));
      
   //failed again undo all
   inv.undo(); 
   inv.undo();
   //nothing to undo
   inv.undo();
   
  }
  
  @Test 
  public void CanRedoIfExecuteFailed() {
    boolean e1 =false ,e2 = false ,r1 = false;
    e1 = inv.execute(new CommandTest("create group and subscriptions"));
    if (e1) {
        e2 = inv.execute(new CommandTest("create group balances",false,true,true));
    
        if(e2 == false) {
            r1 = inv.redo();
        }
    }
    
    assertThat(e1, is(true));
    assertThat(e2, is(false));
    assertThat(r1, is(true));
    
  }

  @Test 
  public void NothingToRedoIfExecuteIsSuccesful() {
    boolean e1 =false ,e2 = false ,r1 = true;
    e1 = inv.execute(new CommandTest("create group and subscriptions"));
    if (e1) {
        e2 = inv.execute(new CommandTest("create group balances",true,true,true));
    
        r1 = inv.redo();
     
    }
    
    assertThat(e1, is(true));
    assertThat(e2, is(true));
    assertThat(r1, is(false));
    
  }

  
  
  
}
