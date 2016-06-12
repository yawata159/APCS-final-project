public class ChanceCard3 implements ChanceCard{

    private final int _id=3;
    private final String _text = "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown."

    public int getId(){
      return _id;
    }

    public void action(Player p){
      if (abs(p.getIntPosition().CompareTo(12))<(p.getIntPosition().CompareTo(28)))
            p.setPosition(board.getSpace(12));
      else p.setPosition(board.getSpace(28));
      if (!p.getPosition().isOwned())
            p.buy();
      //else dice x 10 
    }
}
