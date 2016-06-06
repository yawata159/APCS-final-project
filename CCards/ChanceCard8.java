public class ChanceCard8 implements ChanceCard{

    private final int _id=8;
    private final String _text = "Go to Jail ¨C Go directly to Jail ¨C Do not pass Go, do not collect $200"

    public int getId(){
      return _id;
    }

    public void action(Player p){
      p.setPosition(board.getSpace(24));
    }
}
