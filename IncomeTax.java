public class IncomeTax extends Space{
    
    public IncomeTax(){
	super("Income Tax");
    }

    //To add: Free Parking
    public void land(Player p){
	int x=p.money();
	int tax=Math.min(200,(x/10));
	p.addMoney((-1)*tax);
	FreeParking.add(tax);
    }

}
