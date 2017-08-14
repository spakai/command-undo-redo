package com.spakai.undoredo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class InvokerTest {
  
    
    
  private Invoker inv;  
  
  private Long SUCCESS = 0L;
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

        private final String message;
        private final boolean rt_e,rt_u;
        
        
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
        public ResultInfo execute() {
             System.out.println("Execute " + message);
             if(rt_e) {
                 return new ResultInfo(0L,"");
             } else {
                 return new ResultInfo(99L,"Timed out");
             }

        }
    
        @Override
        public ResultInfo undo() {
             System.out.println("Undo " + message);
              if(rt_u) {
                 return new ResultInfo(0L,"");
             } else {
                 return new ResultInfo(99L,"Timed out");
             }
        }
    }

  @Test 
  public void CanRedoIfExecuteFailed() {
    ResultInfo e1 ,e2 = null,r1 = null;
    e1 = inv.execute(new CommandTest("create group and subscriptions"));
    if (e1.getResultCode() == SUCCESS) {
        e2 = inv.execute(new CommandTest("create group balances",false,true));
    
        if(e2.getResultCode() != SUCCESS) {
            r1 = inv.redo();
        }
    }
    
    assertThat(e1.getResultCode(), is(SUCCESS));
    assertThat(e2.getResultCode(), is(not(SUCCESS)));
    assertThat(r1.getResultCode(), is(not(SUCCESS)));
    
  }

  @Test 
  public void NothingToRedoIfExecuteIsSuccesful() {
    ResultInfo e1 =null ,e2 = null ,r1 = null;
    e1 = inv.execute(new CommandTest("create group and subscriptions"));
    if (e1.getResultCode() == SUCCESS) {
        e2 = inv.execute(new CommandTest("create group balances",true,true));
    
        r1 = inv.redo();
     
    }
    
    assertThat(e1.getResultCode(), is(SUCCESS));
    assertThat(e2.getResultCode(), is(SUCCESS));
    assertThat(r1, is(nullValue()));
    
  }

  @Test 
  public void AbleToRedoUntilAnExecuteIsSuccesful() {
    ResultInfo e1 =null ,e2 = null ,r1 = null, r2 = null, r3 = null;
    e1 = inv.execute(new CommandTest("create group and subscriptions"));
    if (e1.getResultCode() == SUCCESS) {
        e2 = inv.execute(new CommandTest("create group balances",false,false));
        if (e2.getResultCode() != SUCCESS) {
            r1 = inv.redo();
        }
    }
    
    if(r1.getResultCode() != SUCCESS) {
        r2 = inv.redo();
    }
    assertThat(e1.getResultCode(), is(SUCCESS));
    assertThat(e2.getResultCode(), is(not(SUCCESS)));
    assertThat(r1.getResultCode(), is(not(SUCCESS)));
    assertThat(r2.getResultCode(), is(not(SUCCESS)));
    
  }
  
  @Test
  public void CanUndoAPreviousSuccesfulCommand() {
   ResultInfo e1 =null ,e2 = null, u1 = null, u2=null;
   e1 = inv.execute(new CommandTest("create group and subscriptions"));
   e2 = inv.execute(new CommandTest("create group balances"));
      
   
   u1 = inv.undo(); 
   u2 = inv.undo();
   
   assertThat(u2.getResultCode(), is(SUCCESS));
   
  }
  
  @Test
  public void NothingToUndo() {
   inv.execute(new CommandTest("create group and subscriptions"));
   inv.execute(new CommandTest("create group balances"));
      
   //assume failed again undo all
   inv.undo(); 
   inv.undo();
   //nothing to undo though after the last two
   
   ResultInfo ri = inv.undo();
   assertThat(ri, is(nullValue()));
   
  }
}