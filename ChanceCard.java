
/*
 public class ChanceCard{
 
 private int _id;
 
 public ChanceCard(_id){
 
 }
 }
 */


public abstract class ChanceCard{
  
  protected String _text;
  protected int _id;
  private MonopolyGame _game;
  
  public ChanceCard(String text, int id, MonopolyGame game){
    _text=text;
    _id=id;
    _game=game;
  }
  
  public String getText(){
    return _text;
  }
  
  public int getId(){
    return _id;
  }
  
  public abstract void action(Player p);
}
