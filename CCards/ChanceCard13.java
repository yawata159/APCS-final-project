public class ChanceCard13 implements ChanceCard{

    private final int _id=13;
    private final String _text = "You have been elected Chairman of the Board ¨C Pay each player $50"

    public int getId(){
      return _id;
    }

    public void action(Player p){
      //if (p.getPosition() > 24) p.addMoney(200);
      //it would be cool if position has a number...
      p.setPosition(board.getSpace(24));
    }
}