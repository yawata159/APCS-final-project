public class Go extends Space{

    public Go(){
	super("Go");
    }
    public void land(Player p) {
	p.addMoney(200);
    }
}
