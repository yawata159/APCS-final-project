public class ComunityChest extends Space{

    private static ArrayList<ComunityChestCards> cards;

    public ComunityChest(){
	    cards = new ArrayList<ComunityChestCards>();
	    for (int i = 0; i < 16; i++){
	    cards[i] = new ComunityChestCards(i);
	}
    }
}
