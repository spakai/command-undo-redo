# Motivation
At work we have an api that calls Volt APIs (internal) to make database changes.
We have 4 transactions to DB that need to be atomically successful.

The use case is group balance
Say Captain America wanted to create a data group balance called TheAvengers to be shared with other members like Iron Man,SpiderMan , Hulk,Thor etc

After some validations , the group will be need to be created and activated
the 4 APIs to DB are 

- create group and group subscription
- create group balance
- create owner balance
- activate owner subscription



# Design

the requirements is such that 

| Transaction                         | if fails  Undo                                           |     |
| ------------------------------------|----------------------------------------------------------|-----|
| create group and group subscription | Nothing                                                  |     |
| create group balance                | delete group                                             |     |
| create owner balance                | delete group balance, delete group                       |     |
| activate owner subscription         | delete owner balance, delete group balance, delete group |     |

there are 4 terms associated with the command pattern

# command
  Command objects do the transactions above . If use java 8 's functional interface , I should be able to get rid of the implementation classes by using lambdas.Let's see. 
  
```

public interface Command {
    public void execute();
    public void undo();    
    public void redo();
}

```

```

public class CreateGroupAndSubscription implements Command {
  public void execute() {
     
  }
}

```

  
# receiver
  the Volt APIs called by the command objects which then updates the DB.

# invoker
  invokes methods of the receiver, does the invoker keeps the history for undo purposes?
  
  ```
  
  public class Invoker {
     Stack<Command> undoStack;
     Stack<Command> redoStack;
     
     public void storeAndExecute(final Command) {
     
     }
  
  }
  
  ```
  

# client
  This must be the main code that calls the invoker , this will be my unit test class.

`
