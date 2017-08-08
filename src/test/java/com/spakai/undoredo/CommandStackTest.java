package com.spakai.undoredo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class CommandStackTest {
    
  private CommandStack cs;  
  
  @Before
  public void setup() {
      cs = new CommandStack();
  }
  
    class CommandTest implements Command {
        @Override
        public boolean execute() {
            System.out.print("Group Balances created");
            return true;
        }
    
        @Override
        public boolean undo() {
            System.out.print("Group Balances deleted");
            return true;
        }
    }

  @Test
  public void pushACommandIntoStack() {
      CommandTest cmdTest = new CommandTest();
      CommandTest cmdTest2 = new CommandTest();
      cs.push(cmdTest);
      cs.push(cmdTest2);
      
      assertThat(cs.getLastCommand(), is(cmdTest2));
      
      
  }
}