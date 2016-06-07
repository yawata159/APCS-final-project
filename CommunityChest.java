import java.util.LinkedList;
import java.util.List;

public class CommunityChest extends Space{

    private static LinkedList<CommunityCard> _cards;
    private String _name;
    private int pos;

    public CommunityChest(){
	super("Community Chest");
	/*
	Instantiate _cards
	*/
    }

    public CommunityCard communityCard(){
	CommunityCard c=_cards.removeFirst();
	_cards.addLast(c);
	return c;
    }

    public void landed(Player p){
	CommunityCard c=communityCard();
	c.action(p);
    }
}
