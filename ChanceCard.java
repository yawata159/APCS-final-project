/*
public class ChanceCard{
    
    private int _id;

    public ChanceCard(_id){
	
    }
}
*/


public interface ChanceCard{

    public String getText();
    public int getId();
    public void action(Player p);
    private static final MonopolyBoard board = new MonopolyBoard();
}
