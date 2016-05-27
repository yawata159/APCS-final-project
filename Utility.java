public class Utility extends Space implements Buyable{
    
    private final int _price 150;
    private boolean _owned;
    private Player _ owner;
    private boolean _bothOwned;
    //private final int[] _rents = {4,10}


    public Utility(){}


    public int rent(int dice){
	if (bothOwned) return 10 * dice;
	else return 4*dice;
    }

    public boolean isOwned(){
	return _owned;
    }

    public Player owner(){
	return _owner;
    }

    public void bought(Player P){
	_owned = true;
	_owner = P;
    }
