public interface Buyable{
    
    public int getPrice();
    public boolean isOwned();
    public Player owner();
    //public int rent();
    public void bought(Player p);

}
