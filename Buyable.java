import java.util.Scanner;
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
  
  public boolean buyDialogue(){
    Scanner s=new Scanner(System.in);
    String input="";
    while (input!="y" && input!="n"){
      System.out.println("Do you want to buy "+toString()+" for "+
                         getPrice()+" Dollars");
      System.out.print("Enter y or n: ");
      input=s.next();
    }
    if (input.equals("y")) return true;
    else return false;
  }
  
}
