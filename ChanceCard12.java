public class ChanceCard12 implements ChanceCard{

    private final int _id=12;
    private final String _text = "Take a walk on the Boardwalk ¨C Advance token to Boardwalk ";

    public int getId(){
      return _id;
    }

    public void action(Player p){
      p.setPosition(board.getSpace(39));
    }
}
