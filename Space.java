public abstract class Space implements Comparable{

    protected String _name;
    protected int _pos;

    public Space(String name){
	_name=name;
    }

    public String getName(){
	return _name;
    }

    public void setPos(int pos){
	pos=_pos;
    }

    public int getIntPos(){
	return _pos;
    }

    public int compareTo(Object x){
	if (!(x instanceof Space)) return -1;
	Space x1=(Space)(x);
	return _pos-x1._pos;
    }

    public abstract void land(Player p);
}
