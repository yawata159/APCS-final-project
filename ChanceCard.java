import java.util.LinkedList;
import java.util.List;

public abstract class ChanceCard{
    
    private int _id;
    private String _text;
    private final static int NUM_CARDS=17;
    private static MonopolyGame _game;
  
    public ChanceCard(int id, String text){
      _id=id;
      _text=text;
    }

    public static void setGame(MonopolyGame g){
      
    }
    
    public static MonopolyGame getGame(){
      return _game;
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
      cards.add(new Card0(0,"Advance to Go (Collect $200)."));
      cards.add(new Card1(1,"Advance to Illinois Avenue. - If you pass Go, collect $200."));
      cards.add(new Card2(2,"Advance to St. Charles Place. - If you pass Go, collect $200."));
      cards.add(new Card3(3,"Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown."));
      cards.add(new Card4(4,"Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank."));
      cards.add(new Card5(5,"Bank pays you dividend of $50."));
      cards.add(new Card6(6,"Get out of Jail Free - This card may be kept until needed, or traded/sold"));
      cards.add(new Card7(7,"Go Back 3 Spaces."));
      cards.add(new Card8(8, "Go to Jail - Go directly to Jail - Do not pass Go, do not collect $200"));
      cards.add(new Card9(9,"Make general repairs on all your property - For each house pay $25 - For each hotel $100"));
      cards.add(new Card10(10,"Pay poor tax of $15."));
      cards.add(new Card11(11,"Take a trip to Reading Railroad - If you pass Go, collect $200"));
      cards.add(new Card12(12,"Take a walk on the Boardwalk - Advance token to Boardwalk"));
      cards.add(new Card13(13,"You have been elected Chairman of the Board - Pay each player $50"));
      cards.add(new Card14(14,"Your building loan matures. Collect $150."));
      cards.add(new Card15(15,"You have won a crossword competition - Collect $100."));
      cards.add(new Card16(16,"Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank."));
      return cards;
    }

    public static class Card0 extends ChanceCard{

      public Card0(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
        p.setPosition(ChanceCard.getGame().getBoard().getSpace(0));
        p.addMoney(200); }
    }

    public static class Card1 extends ChanceCard{

      public Card1(int id, String text){
        super(id,text);
      }
      
     public void action(Player p){
      if (p.getIntPosition() > 24) p.addMoney(200);
      p.setPosition(ChanceCard.getGame().getBoard().getSpace(24));
    }
    }
    
    public static class Card2 extends ChanceCard{

      public Card2(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
        if (p.getIntPosition() > 11) p.addMoney(200);
        p.setPosition(ChanceCard.getGame().getBoard().getSpace(11));
        (ChanceCard.getGame().getBoard()).getSpace(11).land(p);
      }
    }
    
    public static class Card3 extends ChanceCard{
      public Card3(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
      if (abs(p.getIntPosition().CompareTo(12))<(p.getIntPosition().CompareTo(28)))
            p.setPosition(ChanceCard.getGame().getBoard().getSpace(12));
      else p.setPosition(ChanceCard.getGame().getBoard().getSpace(28));
      if (!((Buyable)p.position()).isOwned())
            p.buy();
      else{
        for (int i = 0; i < 10; i++)
          p.position().land(p);
      }
    }
    }
    
    public static class Card4 extends ChanceCard{

      public Card4(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
        if (p.getIntPosition().CompareTo(5) < 0 && p.getIntPosition().CompareTo(35) > 0 )
            p.setPosition(ChanceCard.getGame().getBoard().getSpace(5));
        else if (p.getIntPosition().CompareTo(15) < 0)
            p.setPosition(ChanceCard.getGame().getBoard().getSpace(15));
        else if (p.getIntPosition().CompareTo(25) < 0)
            p.setPosition(ChanceCard.getGame().getBoard().getSpace(25));
        else
            p.setPosition(ChanceCard.getGame().getBoard().getSpace(35));
        if (!((Buyable)p.position()).isOwned())
            p.buy();
        else{
          for (int i = 0; i < 2; i++){
            p.position().land(p);
          }
        }
    }
    }
    
    public static class Card5 extends ChanceCard{

      public Card5(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
      p.addMoney(50);
    }
    }
    public static class Card6 extends ChanceCard{

      public Card6(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
        p.pickGetOutOfJailFreeCard();
    }
    }
  
    public static class Card7 extends ChanceCard{

      public Card7(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
        p.setPosition(ChanceCard.getGame().getBoard().getSpace(p.getIntPosition() - 3));
        ChanceCard.getGame().getBoard().getSpace(ChanceCard.getGame().getBoard().getSpace(p.getIntPosition() - 3)).land(p);
    }
    }
    
    public static class Card8 extends ChanceCard{

      public Card8(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
        p.setPosition(ChanceCard.getGame().getBoard().getSpace(30));
        ChanceCard.getGame().getBoard().getSpace(30).land(p);
    }
    }
    public static class Card9 extends ChanceCard{

      public Card9(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
        int pay = p.numHousesAndHotels()[0] * 25 + p.numHousesAndHotels()[1] * 100;
        p.addMoney(-1 * pay);
        FreeParking.add(pay);
    }
    }
    public static class Card10 extends ChanceCard{

      public Card10(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
        p.addMoney(-15);
        FreeParking.add(15);
    }
    }
    public static class Card11 extends ChanceCard{

      public Card11(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
      if (p.getIntPosition() > 5) p.addMoney(200);
      p.setPosition(ChanceCard.getGame().getBoard().getSpace(5));
      ChanceCard.getGame().getBoard().getSpace(5).land(p);
    }
    }
    public static class Card12 extends ChanceCard{

      public Card12(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
        p.setPosition(ChanceCard.getGame().getBoard().getSpace(39));
        ChanceCard.getGame().getBoard().getSpace(39).land(p);
    }
    }
    public static class Card13 extends ChanceCard{

      public Card13(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
         for (Player p1: ChanceCard.getGame().getPlayers())
                if (p1!=p){
                    p1.addMoney(50);
                    p.addMoney(-50);
                }
      }
    }
    public static class Card14 extends ChanceCard{

      public Card14(int id, String text){
        super(id,text);
      }
      
     public void action(Player p){
      p.addMoney(150);
    }
    }
    public static class Card15 extends ChanceCard{

      public Card15(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
      p.addMoney(100);
    }
    }
    public static class Card16 extends ChanceCard{

      public Card16(int id, String text){
        super(id,text);
      }
      
      public void action(Player p){
        if (p.getIntPosition().CompareTo(5) < 0 && p.getIntPosition().CompareTo(35) > 0 )
            p.setPosition(ChanceCard.getGame().getBoard().getSpace(5));
        else if (p.getIntPosition().CompareTo(15) < 0)
            p.setPosition(ChanceCard.getGame().getBoard().getSpace(15));
        else if (p.getIntPosition().CompareTo(25) < 0)
            p.setPosition(ChanceCard.getGame().getBoard().getSpace(25));
        else
            p.setPosition(ChanceCard.getGame().getBoard().getSpace(35));
        if (!((Buyable)p.position()).isOwned())
            p.buy();
        else{
          for (int i = 0; i < 2; i++){
            p.position().land(p);
          }
        }
    }
    }
    }


      
/*
public interface ChanceCard{

    public String getText();
    public int getId();
    public void action(Player p);
    public static final MonopolyBoard board = new MonopolyBoard();
}

*/
