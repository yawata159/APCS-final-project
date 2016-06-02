public class ChanceCard15 implements ChanceCard{

    private final int _id=15;
    private final String _text = "You have won a crossword competition - Collect $100."

    public int getId(){
      return _id;
    }

    public void action(Player p){
      p.addMoney(100);
    }
}