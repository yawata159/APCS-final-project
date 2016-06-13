import java.util.LinkedList;
import java.util.List;

public abstract class CommunityChestCard{
    
    private int _id;
    private String _text;
    private static MonopolyGame GAME;
    private final static int NUM_CARDS=17;
  
    public CommunityChestCard(int id, String text){
 _id=id;
 _text=text;
    }

    public static void setGame(MonopolyGame game){
 GAME=game;
    }

    public static MonopolyGame getGame(){
 return GAME;
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

    public static LinkedList<CommunityChestCard> createCards(){
 LinkedList<CommunityChestCard> cards=new LinkedList<CommunityChestCard>();
 cards.add(new Card0(0,"Advance to Go (Collect $200)."));
 cards.add(new Card1(1,"Bank error in your favor – Collect $200"));
 cards.add(new Card2(2,"Doctor's fees {fee} – Pay $50"));
 cards.add(new Card3(3,"From sale of stock you get $50"));
 cards.add(new Card4(4,"Get Out of Jail Free – This card may be kept until needed or sold"));
 cards.add(new Card5(5,"Go to Jail – Go directly to jail – Do not pass Go – Do not collect $200" ));
 cards.add(new Card6(6,"Grand Opera Night – Collect $50 from every player for opening night seats"));
 cards.add(new Card7(7,"Holiday Fund matures - Receive $100"));
 cards.add(new Card8(8,"Income tax refund – Collect $20"));
 cards.add(new Card9(9,"It is your birthday - Collect $10 from each player"));
 cards.add(new Card10(10,"Life insurance matures – Collect $100"));
 cards.add(new Card11(11,"Pay hospital fees of $100"));
 cards.add(new Card12(12,"Pay school fees of $150"));
 cards.add(new Card13(13,"Receive $25 consultancy fee"));
 cards.add(new Card14(14,"You are assessed for street repairs \n- $40 per house \n– $115 per hotel"));
 cards.add(new Card15(15,"You have won second prize in a beauty contest – Collect $10"));
 cards.add(new Card16(16,"You inherit $100 "));
 return cards;
    }

    public static class Card0 extends CommunityChestCard{

 public Card0(int id,String text){
     super(id,text);
 }

 public void action(Player p){
     p.setPosition(CommunityChestCard.getGame().getBoard().getSpace(0));
     p.addMoney(200);//not doubled
 }
    }

    public static class Card1 extends CommunityChestCard{
    
 public Card1(int id,String text){
     super(id, text);
 }

 public void action(Player p){
     p.addMoney(200);
 }

    }

    public static class Card2 extends CommunityChestCard{

 public Card2(int id, String text){
     super(id,text);
 }
 
 public void action(Player p){
     p.addMoney(-50);
     FreeParking.add(50);
 }

    }

    public static class Card3 extends CommunityChestCard{

        public Card3(int id, String text){
            super(id,text);
        }


        public void action(Player p){
     p.addMoney(50);
 }

    }

    public static class Card4 extends CommunityChestCard{

        public Card4(int id, String text){
            super(id,text);
        }

        public void action(Player p){
     p.pickGetOutOfJailFreeCard();
 }

    }

    public static class Card5 extends CommunityChestCard{

        public Card5(int id, String text){
            super(id,text);
        }

        public void action(Player p){
     p.goToJail();
 }

    }

    public static class Card6 extends CommunityChestCard{

        public Card6(int id, String text){
            super(id,text);
        }
 
 public void action(Player p){
     for (Player p1: CommunityChestCard.getGame().getPlayers())
  if (p1!=p){
      p1.addMoney(-50);
      p.addMoney(50);
  }
 }

    }

    public static class Card7 extends CommunityChestCard{

        public Card7(int id, String text){
            super(id,text);
        }

        public void action(Player p){
     p.addMoney(100);
 }

    }

    public static class Card8 extends CommunityChestCard{

        public Card8(int id, String text){
            super(id,text);
        }

        public void action(Player p){
     p.addMoney(20);
 }

    }

    public static class Card9 extends CommunityChestCard{

        public Card9(int id, String text){
            super(id,text);
        }

        public void action(Player p){
     for (Player p1: CommunityChestCard.getGame().getPlayers())
                if (p1!=p){
                    p1.addMoney(-10);
                    p.addMoney(10);
                }
 }

    }

    public static class Card10 extends CommunityChestCard{

        public Card10(int id, String text){
            super(id,text);
        }

        public void action(Player p){
     p.addMoney(100);
 }

    }

    public static class Card11 extends CommunityChestCard{

        public Card11(int id, String text){
            super(id,text);
        }

        public void action(Player p){
     p.addMoney(-100);
     FreeParking.add(100);
 }

    }

    public static class Card12 extends CommunityChestCard{

        public Card12(int id, String text){
            super(id,text);
        }

        public void action(Player p){
     p.addMoney(-150);
     FreeParking.add(150);
 }

    }

    public static class Card13 extends CommunityChestCard{

        public Card13(int id, String text){
            super(id,text);
        }

        public void action(Player p){
     p.addMoney(25);
 }

    }

    public static class Card14 extends CommunityChestCard{

        public Card14(int id, String text){
            super(id,text);
        }

        public void action(Player p){
     int [] x=p.numHousesAndHotels();
     int houses=x[0];
     int hotels=x[1];
     int cost=40*houses+115*hotels;
     p.addMoney((-1)*cost);
     FreeParking.add(cost);
 }

    }

    public static class Card15 extends CommunityChestCard{

        public Card15(int id, String text){
            super(id,text);
        }

        public void action(Player p){
     p.addMoney(10);
 }

    }

    public static class Card16 extends CommunityChestCard{

        public Card16(int id, String text){
            super(id,text);
        }

        public void action(Player p){
     p.addMoney(100);
 }

    }

}

