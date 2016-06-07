import java.util.LinkedList;
import java.util.List;
;
public class Chance extends Space{

    private static LinkedList<ChanceCard> _cards;
    private String _name;
    private int pos;

    public Chance(){
	super("Chance");
	/*
	Instantiate _cards
	*/
    }

    public ChanceCard chanceCard(){
	ChanceCard c=_cards.removeFirst();
	_cards.addLast(c);
	return c;
    }

    public void landed(Player p){
	ChanceCard c=chanceCard();
	c.action(p);
    }
}
