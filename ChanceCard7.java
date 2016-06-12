public class ChanceCard7 implements ChanceCard{

    private final int _id=7;
    private final String _text = "Go Back 3 Spaces.";

    public int getId(){
      return _id;
    }

    public void action(Player p){
        p.setPosition(board.getSpace(p.getIntPosition() - 3));
    }
}
