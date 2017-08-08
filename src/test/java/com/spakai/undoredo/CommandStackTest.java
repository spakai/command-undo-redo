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
      cs = new CommandStack(3);
  }
  
    class DummyOutput extends Output {
        
        public DummyOutput(Long resultCode, String resultMessage) {
            super(resultCode, resultMessage);
        }
        
        
    }
  
    class CommandTest implements Command<DummyOutput> {
        @Override
        public DummyOutput execute() {
             return new DummyOutput(0L,"");
            
        }
    
        @Override
        public DummyOutput undo() {
             return new DummyOutput(0L,"");
            
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
  
  @Test
  public void pushACommandIntoStackRemoveOlderOne() {
      CommandTest cmdTest1 = new CommandTest();
      CommandTest cmdTest2 = new CommandTest();
      CommandTest cmdTest3 = new CommandTest();
      CommandTest cmdTest4 = new CommandTest();
      cs.push(cmdTest1);
      cs.push(cmdTest2);
      cs.push(cmdTest3);
      cs.push(cmdTest4);
      
      assertThat(cs.getSize(), is(3));
      assertThat(cs.getLastCommand(), is(cmdTest4));
  }
  
    @Test
  public void pushCommandsIntoStackAndRetrieveInReverse() {
      CommandTest cmdTest1 = new CommandTest();
      CommandTest cmdTest2 = new CommandTest();
      CommandTest cmdTest3 = new CommandTest();
      CommandTest cmdTest4 = new CommandTest();
      cs.push(cmdTest1);
      cs.push(cmdTest2);
      cs.push(cmdTest3);
      cs.push(cmdTest4);
      
      assertThat(cs.getSize(), is(3));
      assertThat(cs.getLastCommand(), is(cmdTest4));
      assertThat(cs.getLastCommand(), is(cmdTest3));
      assertThat(cs.getLastCommand(), is(cmdTest2));
      assertThat(cs.getLastCommand(), is(nullValue()));
  }
    
  @Test
  public void pushCommandsIntoStackAndRetrieveInReverseAndAddAgain() {
      CommandTest cmdTest1 = new CommandTest();
      CommandTest cmdTest2 = new CommandTest();
      CommandTest cmdTest3 = new CommandTest();
      CommandTest cmdTest4 = new CommandTest();
      cs.push(cmdTest1);
      cs.push(cmdTest2);
      cs.push(cmdTest3);
      cs.push(cmdTest4);
      
      assertThat(cs.getSize(), is(3));
      assertThat(cs.getLastCommand(), is(cmdTest4));
      assertThat(cs.getLastCommand(), is(cmdTest3));
      assertThat(cs.getLastCommand(), is(cmdTest2));
      assertThat(cs.getLastCommand(), is(nullValue()));
      
      
      cs.push(cmdTest1);
      assertThat(cs.getLastCommand(), is(cmdTest1));
  }
  
  
  @Test
  public void getTheLastCommandAgain() {
      CommandTest cmdTest1 = new CommandTest();
      
      cs.push(cmdTest1);
      
      assertThat(cs.getLastCommand(), is(cmdTest1));
      assertThat(cs.getAgainLastCommand(), is(cmdTest1));
  }
  
  @Test
  public void X() {
      CommandTest cmdTest1 = new CommandTest();
      CommandTest cmdTest2 = new CommandTest();
      CommandTest cmdTest3 = new CommandTest();
      
      cs.push(cmdTest1);
      cs.push(cmdTest2);
      cs.push(cmdTest3);      
      assertThat(cs.getLastCommand(), is(cmdTest3));
      assertThat(cs.getAgainLastCommand(), is(cmdTest3));
      assertThat(cs.getLastCommand(), is(cmdTest3));
      
  }
  
  
}