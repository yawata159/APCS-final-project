public class ChanceCard5 implements ChanceCard{

    private final int _id=5;
    private final String _text = "Bank pays you dividend of $50."

    public int getId(){
      return _id;
    }

    public void action(Player p){
      p.addMoney(50);
    }
}