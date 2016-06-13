public abstract class Space{
  
  protected String _name;
  protected int _pos;
  protected MonopolyGame _g;
  
  public Space(String name){
    _name=name;
    _g=null;//OVERWRITTEN LATER --> lack of developmental foresight, ,
  }
  
  public void setGame(MonopolyGame g){
    _g=g;
  }
  
  public MonopolyGame getGame(){
    return _g;
  }
  
  public String getName(){
    return _name;
  }
  
  public void setPos(int pos){
    _pos=pos;
  }
  
  public int getIntPos(){
    return _pos;
  }
  
  public int compareTo(Object x){
    if (!(x instanceof Space)) return -1;
    Space x1=(Space)(x);
    return _pos-x1._pos;
  }
  
  public String toString(){
    return getName();
  }
  
  public abstract void land(Player p);
}
