public class Railroad extends Space implements Buyable{
    
    private final int _price = 200;
    private boolean _owned;
    private Player _owner;
    private final int[] _rents = {25,50,100,200};
    
    public Railroad(String name){
	super(name);
    }

    public int getPrice(){
	return _price;
    }

    public boolean isOwned(){
	return _owned;
    }

    public Player owner(){
	return _owner;
    }

    public int rent(int numOwned){
	return _rents[numOwned];
    }

    public void bought(Player p){
	_owned=true;
	_owner=p;
    }

}
