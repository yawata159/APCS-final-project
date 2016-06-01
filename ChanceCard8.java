public class ChanceCard8 implements ChanceCard{

    private final int _id=8;
    private final String _text = "Go to Jail ¨C Go directly to Jail ¨C Do not pass Go, do not collect $200"

    public int getId(){
      return _id;
    }

    public void action(Player p){
      //if (p.getPosition() > 24) p.addMoney(200);
      //it would be cool if position has a number...
      p.setPosition(board.getSpace(24));
    }
}