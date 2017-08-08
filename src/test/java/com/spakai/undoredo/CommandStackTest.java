package com.spakai.undoredo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class CommandStackTest {
  
  @Before
  public void setup() {

  }
  
class CommandTest implements Command {
    @Override
    public void execute() {
        System.out.print("Group Balances created");
    }
    
    @Override
    public void undo() {
        System.out.print("Group Balances deleted");
    }
}

  @Test
  public void pushACommandIntoStack() {
      assertThat(1,is(1));
      
  }
}