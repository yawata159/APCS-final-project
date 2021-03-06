import java.util.ArrayList;
import java.util.List;

public class Player{
  
  private MonopolyGame _game;//Through game access is granted to board and other things
  private String _name;//idea: avatar, puuppy , flower, arrow
  private int _money;
  private Space _position;
  private boolean[] _monopolies;//_monopolies[i] true iff player has the ith monopopyly
  private ArrayList<Buyable> _owned;
  private int _railroadsOwned;
  private int _utilitiesOwned;
  private boolean _inJail; 
  private int _getOutOfJailFreeCards;
  private char _avatar; //displayed in the terminal
    private int _color; //see above
    private int _jailDoubles;

  public Player(String name, int money, char avatar, int color, MonopolyGame g){
    _game=g;
    _name=name;
    _money=money;
    _position=null;
    _monopolies=new boolean[8];
    for (int i=0;i<8;i++)
      _monopolies[i]=false;
    _owned=new ArrayList<Buyable>();
    _railroadsOwned=0;
    _utilitiesOwned=0;
    _avatar = avatar;
    _color = color;
    _jailDoubles = 0;
  }
  
    public ArrayList<Buyable> getOwned() {
	return _owned;
    }

  public String name(){
    return _name;
  }
  
  public char getAvatar() {
    return _avatar;
  }
  
  public void setAvatar(char c) {
    _avatar = c;
  }
  
  public int getColor() {
    return _color;
  }
  
  public void setColor(int c) {
    _color = c;
  }

    public int getJailDoubles() {
	return _jailDoubles;
    }
    public void setJailDoubles(int n) {
	_jailDoubles= n;
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
  
  public int getIntPos(){
    return position().getIntPos();
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
  
  public int numGetOutOfJailFreeCards(){
    return _getOutOfJailFreeCards;
  }
  
  public void pickGetOutOfJailFreeCard(){
    _getOutOfJailFreeCards++;
  }
    public void useGetOutOfJailFreeCard() {
	_getOutOfJailFreeCards--;
    }
  public boolean inJail(){
    return _inJail;
  }
  
  public void goToJail(){
    setPosition(_game.getBoard().getSpace(10));
    _inJail=true;
  }

    public void exitJail(){
	_inJail = false;
    }
  
  //money can be <0
  public void addMoney(int money){
    _money+=money;
  }
  
  public void payMoney(int money, Player other){
    addMoney(-1*money);
    other.addMoney(money);
  }
  
  public boolean[] monopolies(){
    return _monopolies;
  }
  
  public boolean checkMonopoly(int type){
    int owned=0;
    for (Buyable b: _owned)
      if (b instanceof Property){
      Property p=(Property)(b);
      if (p.getType()==type) owned++;
    }
    if ((type==1 || type==8) && owned==2) return true;
    else if (owned==3) return true;
    return false;
  }
  
  public void buy(){
    if (!(position() instanceof Buyable)){
      System.out.println("This position is not buyable");
      return;
    }
    Buyable pos1=(Buyable)(position());
    buy(pos1);
  }
  
  public void buy(Buyable b){
    if (_money<b.getPrice()){
      System.out.println("Not enough money");
      return;
    }
    b.bought(this);
    _owned.add(b);
    if (b instanceof Property){
      Property p=(Property)(b);
      int type=p.getType();
      if(checkMonopoly(type)){
        _monopolies[type]=true;
        for (Property q: _game.getBoard().getProperties()){
          if (q.getType()==type)
            q.setMonopoly();
        }
      }
    }
    if (b instanceof Railroad)
      _railroadsOwned++;
    if (b instanceof Utility)
      _utilitiesOwned++;
    addMoney(0-b.getPrice());
  }
  
  public void buyHouse(Property p){
    if (_money<p.housePrice()){
      System.out.println("Not enough money");
      return;
    }
    if (p.addHouse()) addMoney((-1)*p.housePrice());
  }
  
  public int[] numHousesAndHotels(){
    int ret[]=new int[2];
    for (Buyable b: ownings()){
      if (b instanceof Property){ 
        Property p=(Property)(b);
        int x=p.numHouses();
        if (x<5) ret[0]+=x;
        else ret[1]+=1; //Hotel
      }
    }
    return ret;
  }
  
  //just for debugging
  public String toString() {
      return getIntPos() + "";
  }
}
