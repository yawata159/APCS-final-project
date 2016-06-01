public class ChanceCard10 implements ChanceCard{

    private final int _id=10;
    private final String _text = "Pay poor tax of $15."

    public int getId(){
      return _id;
    }

    public void action(Player p){
      p.addMoney(15);
    }
}