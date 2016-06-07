public class GoToJail extends Space{

    public GoToJail(){
	super("Go To Jail");
    }

    public void land(Player p){
	p.goToJail();
    }

}
