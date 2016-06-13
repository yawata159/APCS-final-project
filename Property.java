public class Property extends Buyable{
  
  private int _housePrice;
  private int _numHouses;
  //private boolean _isHotel;
  private int[] _rents;//_rents[i] is price with i houses
  private int _type;//used for monopoly
  private boolean _isMonopoly;
  
  public Property(int type, int price, String name, int[] rents){
    super(name, price);
    _housePrice=50*(1+(type-1)/2);
    _rents=rents;
    _type=type;
    _isMonopoly=false;
  }
  
  public int housePrice(){
    return _housePrice;
  }
  
  public int numHouses(){
    return _numHouses;
  }
  
  public boolean addHouse(){
    if (numHouses()>=5){
      System.out.println("You already have the maximum number of houses");
      return false;
    }
    _numHouses++;
    return true;
  }
  
  public boolean isMonopoly(){
    return _isMonopoly;
  }
  
  public void setMonopoly(){
    _isMonopoly=true;
  }
  
  public int getType(){
    return _type;
  }
  
  public int rent(){
    int a=numHouses();
    //Rent doubled in monopoly
    if (a==0 && isMonopoly())
      return 2*_rents[0];
    
    return _rents[a];
  }
  
  public void bought(Player buyer){
    _isOwned=true;
    _owner=buyer;
  }
  
  public void land(Player p){
    if (isOwned()){
      p.addMoney((-1)*rent());
      owner().addMoney(rent());
    }
    else if (buyDialogue()) 
      p.buy(this);
  }
}

    
