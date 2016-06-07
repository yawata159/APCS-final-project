public abstract class Buyable extends Space{

    protected int _price;
    protected boolean _isOwned;
    protected Player _owner;

    public Buyable(String name, int price){
	super(name);
	_price=price;
	_isOwned=false;
	_owner=null;
    }

    public int getPrice(){
	return _price;
    }

    public boolean isOwned(){
	return _isOwned;
    }

    public Player owner(){
	return _owner;
    }
    
    public void bought(Player p){
	_isOwned=true;
	_owner=p;
    }

}
