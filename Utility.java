public class Utility extends Buyable{
    
    private final int _price=150;
    private boolean _owned;
    private Player _owner;
    private boolean _bothOwned;
    //private final int[] _rents = {4,10}


    public Utility(String name){
	super(name,150);
    }


    public int rent(int dice){
	if (_bothOwned) return 10 * dice;
	else return 4*dice;
    }

    public void land(Player p){
	if (isOwned()){
            int x=owner().railroadsOwned();
            int rent=rent(x);
            p.addMoney((-1)*x);
            owner().addMoney(x);
        }
        else{}
    }

}
