import java.util.LinkedList;
import java.util.List;

public class CommunityChest extends Space{

    private static LinkedList<CommunityChestCard> _cards;
    private static int _drawn;

    public CommunityChest(){
	super("CommunityChest");
	_drawn=0;
	_cards=CommunityCard.createCards();
	CommuityChestCard.setGame(getGame());
    }

    public CommunityCard communityChestCard(){
	_drawn++;
	CommunityChestCard c=_cards.removeFirst();
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
	CommunityChestCard c=communityChestCard();
	c.action(p);
    }
}
