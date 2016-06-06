public class ChanceCard11 implements ChanceCard{

    private final int _id=11;
    private final String _text = "Take a trip to Reading Railroad ¨C If you pass Go, collect $200"

    public int getId(){
      return _id;
    }

    public void action(Player p){
      if (p.getPosition() > 5) p.addMoney(200);
      p.setPosition(board.getSpace(5));
    }
}
