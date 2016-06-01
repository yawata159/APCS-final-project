public class Chance extends Space{

    private static ArrayList<ChanceCard> cards;

    public Chance(){
	cards = new ArrayList<ChanceCard>();
	for (int i = 0; i < 16; i++){
	    cards[i] = new ChanceCard(i);
	}
    }
}
