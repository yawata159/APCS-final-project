public class ChanceCard0 implements ChanceCard{

    private final int _id=0;
    private final String _text = "Advance to Go (Collect $200)."

    public int getId(){
 return _id;
    }

    public void action(Player p){
 p.setPosition(board.getSpace(0));
 p.addMoney(200);//not doubled
    }
}