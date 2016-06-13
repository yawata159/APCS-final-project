public class Railroad extends Buyable{
  
  private final int[] _rents = {25,50,100,200};
  
  public Railroad(String name){
    super(name,200);
  }
  
  public int rent(int numOwned){
    return _rents[numOwned];
  }
  
  public void land(Player p){
    if (isOwned()){
      int x=owner().railroadsOwned();
      int rent=rent(x);
      p.addMoney((-1)*x);
      owner().addMoney(x);
    }
    else{
      if (buyDialogue())
        p.buy(this);
    }
  }
  
}
