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
        private boolean rt_e,rt_u;
        
        
        public CommandTest(String message) {
            this.message = message;
            this.rt_e = true;
            this.rt_u = true;
        
        }
        
        public CommandTest(String message,boolean rt_e, boolean rt_u) {
            this.message = message;
            this.rt_e = rt_e;
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
             return rt_u;
        }
        
        @Override
        public boolean redo() {
             System.out.println("Redo " + message);
             return false;
             
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
    boolean e1 =false ,e2 = false ,r1 = true;
    e1 = inv.execute(new CommandTest("create group and subscriptions"));
    if (e1) {
        e2 = inv.execute(new CommandTest("create group balances",false,true));
    
        if(e2 == false) {
            r1 = inv.redo();
        }
    }
    
    assertThat(e1, is(true));
    assertThat(e2, is(false));
    assertThat(r1, is(false));
    
  }

  @Test 
  public void NothingToRedoIfExecuteIsSuccesful() {
    boolean e1 =false ,e2 = false ,r1 = true;
    e1 = inv.execute(new CommandTest("create group and subscriptions"));
    if (e1) {
        e2 = inv.execute(new CommandTest("create group balances",true,true));
    
        r1 = inv.redo();
     
    }
    
    assertThat(e1, is(true));
    assertThat(e2, is(true));
    assertThat(r1, is(false));
    
  }

  @Test 
  public void RedoUntilAnExecuteIsSuccesful() {
    boolean e1 =false ,e2 = false ,r1 = false, r2 = false;
    e1 = inv.execute(new CommandTest("create group and subscriptions"));
    if (e1) {
        e2 = inv.execute(new CommandTest("create group balances",false,false));
       
        r1 = inv.redo();
        if ( r1 == false) {
            r2 = inv.redo();
        }
     
    }
    
    assertThat(e1, is(true));
    assertThat(e2, is(false));
    assertThat(r1, is(false));
    assertThat(r2, is(false));
    
  }
  
  
  
}
