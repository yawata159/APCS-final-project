public class Utility extends Buyable{
  
  private final int _price=150;
  private boolean _owned;
  private Player _owner;
  private int _dice;
  
  public Utility(String name){
    super(name,150);
  }
  
  public void updateDice(int dice){
    _dice=dice;
  }
  
  public int rent(int dice){
    if (_owner.utilitiesOwned()==2) return 10 * dice;
    else return 4*dice;
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
  
}
