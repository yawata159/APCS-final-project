public class Utility extends Buyable{
  
  private final int _price=150;
  private boolean _owned;
  private Player _owner;
  private static int _dice;
  
  public Utility(String name){
    super(name,150);
  }
  
  public static void updateDice(int dice){
    _dice=dice;
  }
  
  public int rent(int dice){
    if (_owner.utilitiesOwned()==2) return 10 * _dice;
    else return 4*_dice;
  }
  
  public void land(Player p){
    if (isOwned()){
      int rent=rent(_dice);
      p.addMoney((-1)*rent);
      owner().addMoney(rent);
    }
    else{
      if (buyDialogue())
        p.buy(this);
    }
  }
  
  
  //For Chance card landing only
  public void land(Player p, boolean b){
    if (isOwned()){
      int rent=10*_dice;
      p.addMoney((-1)*rent);
      owner().addMoney(rent);
    }
    else{
      if (buyDialogue())
        p.buy(this);
    }
  }
  
}
