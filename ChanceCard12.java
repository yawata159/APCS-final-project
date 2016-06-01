public class ChanceCard12 implements ChanceCard{

    private final int _id=12;
    private final String _text = "Take a walk on the Boardwalk ¨C Advance token to Boardwalk "

    public int getId(){
      return _id;
    }

    public void action(Player p){
      //if (p.getPosition() > 24) p.addMoney(200);
      //it would be cool if position has a number...
      p.setPosition(board.getSpace(24));
    }
}