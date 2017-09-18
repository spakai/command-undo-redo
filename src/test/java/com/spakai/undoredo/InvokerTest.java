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
  
    class CallbackImpl implements Callback<DummyOutput> {
    @Override
        public DummyOutput onSuccess(DummyOutput response) {
            System.out.println(response.getResultCode());
            return response;
        }
        
    }

    
    class CommandTest implements Command<DummyOutput, DummyOutput> {

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
        public DummyOutput execute() {
             System.out.println("Execute " + message);
             if(rt_e) {
                 return new DummyOutput(0L,"");
             } else {
                 return new DummyOutput(99L,"Timed out");
             }

        }
    
        @Override
        public DummyOutput undo() {
             System.out.println("Undo " + message);
              if(rt_u) {
                 return new DummyOutput(0L,"");
             } else {
                 return new DummyOutput(99L,"Timed out");
             }
        }
    }

  
  @Test
  public void CanUndoAPreviousSuccesfulCommand() {
   DummyOutput e1 =null ,e2 = null, u1 = null, u2=null;
   inv.execute(new CommandTest("create group and subscriptions"), new CallbackImpl());
   inv.execute(new CommandTest("create group balances"), new CallbackImpl());
      
   while(inv.isUndoAvailable()) {
       inv.undo(); 
   }
   
   
  }
}
