public class ChanceCard2 implements ChanceCard{

    private final int _id=2;
    private final String _text = "Advance to St. Charles Place. - If you pass Go, collect $200."

    public int getId(){
      return _id;
    }

    public void action(Player p){
      //if (p.getPosition() > 11) p.addMoney(200);
      //it would be cool if position has a number...
      p.setPosition(board.getSpace(11));
    }
}