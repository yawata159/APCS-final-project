import java.util.ArrayList;
import java.util.List;

public class Player{

    private String _name;//idea: avatar, puuppy , flower, arrow
    private int _money;
    private Space _position;
    private boolean[] _monopolies;//_monopolies[i] true iff player has the ith monopopyly
    private ArrayList<Buyable> _owned;
    private int _railroadsOwned;
    private int _utilitiesOwned;

    public Player(String name, int money){
	_name=name;
	_money=money;
	_position=null;
	_monopolies=new boolean[8];
	for (int i=0;i<8;i++)
	    _monopolies[i]=false;
	_owned=new ArrayList<Buyable>();
	_railroadsOwned=0;
	_utilitiesOwned=0;
    }

    public String name(){
	return _name;
    }

    public int money(){
	return _money;
    }

    public void setPosition(Space s){
	_position=s;
    }

    public Space position(){
	return _position;
    }

    public ArrayList<Buyable> ownings(){
	return _owned;
    }

    public int railroadsOwned(){
	return _railroadsOwned;
    }

    public int utilitiesOwned(){
	return _utilitiesOwned;
    }

    //money can be <0
    public void addMoney(int money){
	_money+=money;
    }

    public void payMoney(int money, Player other){
	addMoney(-1*money);
	other.addMoney(money);
    }

    public void buy(){
	if (!(position() instanceof Buyable)) throw new IllegalArgumentException();
	Buyable position1=(Buyable)(position());
    }

    public void buy(Buyable b){
	b.bought(this);
	_owned.add(b);
	/*
	if (b instanceof Railroad)
	    _railroadsOwned++;
	if (b instanceof Utility)
	    _utilitiesOwned++;
	*/
    }

}
