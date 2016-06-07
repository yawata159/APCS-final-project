public class FreeParking extends Space{
    
    private static int _money;

    public FreeParking(){
	super("Free Parking");
    }

    public static void add(int money){
	_money+=money;
    }

    public void land(Player p){
	p.addMoney(_money);
	_money=0;
    }

}
