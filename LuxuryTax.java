public class LuxuryTax extends Space{

    public LuxuryTax(){
	super("Luxury Tax");
    }

    public void land(Player p){
	p.addMoney(-75);
	FreeParking.add(75);
    }

}
