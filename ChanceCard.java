import java.util.LinkedList;
import java.util.List;

public abstract class ChanceCard{
    
    private int _id;
    private String _text;
    private final static int NUM_CARDS=17;
  
    public ChanceCard(int id, String text){
	_id=id;
	_text=text;
    }

    public int getId(){
	return _id;
    }

    public String getText(){
	return _text;
    }

    public abstract void action(Player p);

    public String toString(){
	return getText();
    }

    public static LinkedList<ChanceCard> createCards(){
	LinkedList<ChanceCard> cards=new LinkedList<ChanceCard>();
	cards.add(new Card0());
	cards.add(new Card1());
	/*
	  ...
	*/
	cards.add(new Card15());
    }

    public class Card0 extends ChanceCard{

	public Card0(){
	    new ChanceCard(0,"Advance to Go (Collect $200).");
	}

	public void action(Player p){
	    p.setPosition(board.getSpace(0));
	    p.addMoney(200);//not doubled
	}
    }

    public class card1{}
    /*
      ...
    */
    public class card15{}

}

/*
public interface ChanceCard{

    public String getText();
    public int getId();
    public void action(Player p);
    public static final MonopolyBoard board = new MonopolyBoard();
}

*/
