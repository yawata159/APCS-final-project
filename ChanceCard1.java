public class ChanceCard1 implements ChanceCard{

    private final int _id=1;
    private final String _text = "Advance to Illinois Avenue. - If you pass Go, collect $200.";

    public int getId(){
      return _id;
    }

    public void action(Player p){
      if (p.getIntPos() > 24) p.addMoney(200);
      p.setPosition(board.getSpace(24));
    }
}
