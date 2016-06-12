public class ChanceCard14 implements ChanceCard{

    private final int _id=14;
    private final String _text = "Your building loan matures. Collect $150.";

    public int getId(){
      return _id;
    }

    public void action(Player p){
      p.addMoney(150);
    }
}
