import java.util.LinkedList;
import java.util.List;

public class Chance extends Space{

    private static LinkedList<ChanceCard> _cards;
    private static int _drawn;

    public Chance(){
	super("Chance");
	_drawn=0;
	_cards=ChanceCard.createCards();
    }

    public ChanceCard chanceCard(){
	_drawn++;
	ChanceCard c=_cards.removeFirst();
	_cards.addLast(c);
	if (_drawn==_cards.size()){
	    _drawn=0;
	    shuffle();
	}
	return c;
    }

    public static void shuffle(){
	//
	//
	//
    }

    public void land(Player p){
	ChanceCard c=chanceCard();
	c.action(p);
    }
}
