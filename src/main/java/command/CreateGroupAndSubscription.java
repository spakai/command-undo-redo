package command;

public class CreateGroupAndSubscription implements Command{
    @Override
    public void execute() {
        System.out.print("Group and Subscription created");
    }
    
}
