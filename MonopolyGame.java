import java.util.*;
import java.io.*;

public class MonopolyGame{
  
    private static final String RESET = "[2J[1;1H";
  
    public ArrayList<Player> _players;
    public MonopolyBoard _board;
    public MapText _map;
  
    /*
      ...
    */
  
    public MonopolyGame() throws FileNotFoundException {
	_players = new ArrayList<Player>();
	Scanner s=new Scanner(System.in);
	System.out.print(RESET);
	System.out.println("[1mMonopoly! For up to 6 players");
	System.out.println("[1mMake sure you are using this on [4mterminal[0;1m and that it is zoomed out enough for the line below to fit on one line (unless you like messed up ascii maps)");
	System.out.println("[1m|**********************************************************************************************************************************************************************|");
	System.out.println("[0m");
	while (_players.size() < 6){
	    System.out.print("Type name of this player(or write START to start playing): ");
	    String name=s.next();
      
	    if (name.equalsIgnoreCase("START"))  {
		if (_players.size() <= 1) System.out.println("Not enough Players");
		else break;
	    }
      
	    else {
		System.out.print("Type the avatar you want to use for this player(ONE character): ");
		char av = getChar(s);
        
		System.out.print("Type the color you want to use for your avatar (BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE): ");
		int color = getColor(s);
		_players.add(new Player(name, 1500, av, color, this));
	    }
	    System.out.println();
	}
	//    s.close();
	_board=new MonopolyBoard();
    
	//set positions to go
	for (int i = 0 ; i < _players.size(); i++) {
	    _players.get(i).setPosition(_board.getSpace(0)); 
	}
    
	_map=new MapText(_players);
    
    
    }
  
    private static char getChar(Scanner s) {
	String str = s.next();
	if (str.length() != 1) {
	    System.out.print("I said one character: ");
	    return getChar(s);
	}
	else 
	    return str.charAt(0);
    }
  
    private static int getColor(Scanner s) {
	String str = s.next();
	String[] colorList = {"BLACK", "RED", "GREEN", "YELLOW", "BLUE", "MAGENTA", "CYAN", "WHITE"};
	for (int i = 0 ; i < colorList.length; i++) {
	    if (str.equalsIgnoreCase(colorList[i])) return 30+i;
	}
	System.out.print("I'm afraid you can't do that: ");
	return getColor(s);
    }
  
  
    public int numPeopleWithMoney() {
	int sum = 0;
	for (Player p : _players) 
	    if (p.money() > 0)
		sum += 1;	
	return sum;
    }
  
    public MonopolyBoard getBoard(){
	return _board;
    }
  
    public MapText getMap() {
	return _map;
    }
  
    public ArrayList<Player> getPlayers() {
	return _players;
    }
    
    public void playerTurn(Scanner s, Player p) {
	if (p.inJail()) {
	    System.out.print("You're in jail. Do you want to throw DOUBLES, pay a $50 FINE, or use a Get Out of Jail CARD?: ");
	    int jailActionNum = inJailActions(s, p);
	    if (jailActionNum == 0){
		if (p.getJailDoubles() == 3) {
		    System.out.println("You've already rolled three turns. Pay the 50$ Fine");
		    p.addMoney(-50);
		    p.exitJail();
		}
		else {
		    int dice10 = (int)(Math.random()*6 +1);
		    int dice20 = (int)(Math.random()*6+1);
		  
		    System.out.println("You rolled " + dice10 + " and " + dice20);
		    if (dice10 == dice20) {
			System.out.println("You're Free!");
			p.exitJail();
			p.setJailDoubles(0);
		    }
		    else {
			System.out.println("Those aren't doubles. Wait another turn");
			p.setJailDoubles(p.getJailDoubles() + 1);
			return;
		    }
		}
	    }
	    if (jailActionNum == 1){
		System.out.println("You pay the fine");
		p.addMoney(-50);
		p.exitJail();
		  
	    }
	    if (jailActionNum == 2){
		if (p.numGetOutOfJailFreeCards() > 0) {
		    System.out.println("You use a get out of jail card");
		    p.useGetOutOfJailFreeCard();
		    p.exitJail();
		}
		else {
		    System.out.println("You don't have any get out of jail cards.");
		    return;
		}
	    }
	}

     
	//dice rolls
	System.out.print("Type anything to roll: ");
	s.next();
	
	int dice1 = (int)(Math.random()*6 +1);
	int dice2 = (int)(Math.random()*6+1);

	System.out.println("You rolled " + dice1 + " and " + dice2);
	if (dice1 == dice2) {
	    System.out.println("1 Double!");	
	    dice1 = (int)(Math.random()*6+1);
	    dice2 = (int)(Math.random()*6+1);
	    System.out.println("You rolled " + dice1 + " and " + dice2);

	    if (dice1==dice2){
		System.out.println("2 Doubles!");
		dice1 = (int)(Math.random()*6+1);
		dice2 = (int)(Math.random()*6+1);
		System.out.println("You rolled " + dice1 + " and " + dice2);

		if (dice1 == dice2) {
		    System.out.print("3 Doubles! Jail For you!");
		    p.goToJail();
		    return;
		}
	    }
	}
	int oldPosInt = p.position().getIntPos();
	int newPosInt = (p.position().getIntPos() + dice1 +dice2) % 40;
	Space newPos = getBoard().getSpace(newPosInt); 
	p.setPosition(newPos);
	System.out.println("You landed on " + newPos.getName());
	
	if (newPosInt - oldPosInt < 0) {
	    System.out.println("Passed Go. You get $200");
	    p.addMoney(200);
	}
	
	//debug:
	/*      System.out.println(newPos.getIntPos());
		System.out.println(p);
		for (Player x : _players) System.out.print(x + ", " );
		System.out.println();
	*/

	// 20(free parking) and 10(jail) do nothing
	//nonjail spaces:
	if (newPosInt == 4) { //income tax
	    int x=p.money();
	    int tax=Math.min(200,(x/10));
	    p.addMoney((-1)*tax);
	    System.out.println("You pay a tax of $200/10% to the bank");
	}
	
	else if (newPosInt == 38) { //luxury tax;
	    p.addMoney(-75);
	    System.out.println("You pay a luxury tax of $75");
	}
	
	else if (newPosInt == 0) { //go
	    p.addMoney(200);
	    System.out.println("You pass GO and collect $200");
	}
	else if (newPosInt == 10) {
	    System.out.println("Hopefully, you're just visiting the jail");
	}
	else if (newPosInt == 20) {
	    System.out.println("Free Parking!");
	}
	else if (newPosInt == 30) { //gotojail
	    p.goToJail();
	    System.out.println("You go to Jail");
	}
	//chance:
	else if (newPosInt == 7 || newPosInt == 22 || newPosInt == 36) {
	    System.out.println("You take a Chance Card. What could it be?");
	    int num = (int)(Math.random()*16);
	    if (num == 0){
		System.out.println("Advance to Go (Collect $200)");
		p.setPosition(getBoard().getSpace(0));
		p.addMoney(200);
	    }
	    else if (num == 1){
		System.out.println("Advance to Illinois Avenue - If you pass Go, collect $200");
		if (p.getIntPos() > 24) p.addMoney(200);
		p.setPosition(getBoard().getSpace(24));
	    }
	    else if (num == 2){
		System.out.println("Advance to St. Charles Place - If you pass Go, collect $200");
		if (p.getIntPos() > 11) p.addMoney(200);
		p.setPosition(getBoard().getSpace(11));
	    }
		    
	    else if (num == 3){
		System.out.println("Advance token to nearest Utility.");
		if ((Math.abs(p.getIntPos()-12))<(Math.abs(p.getIntPos()-28)))
		    p.setPosition(getBoard().getSpace(12));
		else p.setPosition(getBoard().getSpace(28));
	    }
	    else if (num == 4){
		System.out.println("Advance token to nearest Railroad");
		if (p.getIntPos()< 10)
		    p.setPosition(getBoard().getSpace(5));
		else if (p.getIntPos()< 20)
		    p.setPosition(getBoard().getSpace(15));
		else if (p.getIntPos()< 30)
		    p.setPosition(getBoard().getSpace(25));
		else
		    p.setPosition(getBoard().getSpace(35));
	    }
	    else if (num == 5){
		System.out.println("Bank pays you dividend of $50");
		p.addMoney(50);
	    }
	    else if (num == 6){
		System.out.println("Get Out of Jail Free Card!");
		p.pickGetOutOfJailFreeCard();
	    }
	    else if (num == 7){
		System.out.println("Go back 3 spaces");
		p.setPosition(getBoard().getSpace((p.getIntPos() + 37) %40));
	    }
	    else if (num == 8){
		System.out.println("Go directly to Jail");
		p.goToJail();
	    }
	    else if (num == 9){
		System.out.println("Make general repairs - For each house pay $25, for each hotel $100");
		p.addMoney(0-p.numHousesAndHotels()[0]*25 + p.numHousesAndHotels()[1]*100);
	    }
	    else if (num == 10){
		System.out.println("Pay poor tax of $15");
		p.addMoney(-15);
	    }
	    else if (num == 11){
		System.out.println("Take a trip to Reading Railroad - If you pass Go, collect $200");
		if (p.getIntPos() > 5) p.addMoney(200);
		p.setPosition(getBoard().getSpace(5));
		
	    }
	    else if (num == 12){
		System.out.println("Take a walk on the Boardwalk");
		p.setPosition(getBoard().getSpace(29));
	    }
	    else if (num == 13){
		System.out.println("You have been leceted Chairman of the Board - Pay each player $50");
		for (Player play: getPlayers())
		    if (play != p) play.addMoney(50);
		p.addMoney(-50*(getPlayers().size()-1));
	    }
	    else if (num == 14){
		System.out.println("Your building loan matures. Collect $150");
		p.addMoney(150);
	    }
	    else if (num == 15){
		System.out.println("You have won a crossword competition - COllect $100");
		p.addMoney(100);
	    }
	    
	}

	//comunity:
	else if (newPosInt == 2 || newPosInt == 17 || newPosInt == 33) {
	    System.out.println("You take a Community Chest Card. What could it be?");
	    int num = (int)(Math.random()*16);
	    if (num == 0) {
		System.out.println("Advance to Go (Collect $200)");
		p.setPosition(getBoard().getSpace(0));
		p.addMoney(200);
	    }
	    else if (num == 1) {
		System.out.println("Bank error in your favor - Collect $200");
		p.addMoney(200);
	    }
	    else if (num == 2) {
		System.out.println("Doctor's fees - Pay $50");
		p.addMoney(-50);
	    }
	    else if (num == 3) {
		System.out.println("From sale of stock you get $50");
		p.addMoney(50);
	    }
	    else if (num == 4) {
		System.out.println("Get Out of Jail Free Card!");
		p.pickGetOutOfJailFreeCard();
	    }
	    else if (num == 5) {
		System.out.println("Go directly to Jail");
		p.goToJail();
	    }
	    else if (num == 6) {
		System.out.println("Grand Opera Night. Collect $50 from every player");
		for (Player play: getPlayers())
		    if (play != p)
			play.addMoney(-50);
		p.addMoney(50*(getPlayers().size()-1));
	    }
	    else if (num == 7) {
		System.out.println("Holiday Fund matures - Receive $100");
		p.addMoney(100);
	    }
	    else if (num == 8) {
		System.out.println("Income tax refund - Collect $20");
		p.addMoney(20);
	    }
	    else if (num == 9) {
		System.out.println("It is your birthday - Collect $10 from Every Player");
		for (Player play: getPlayers())
		    if (play != p)
			play.addMoney(-10);
		p.addMoney(10*(getPlayers().size()-1));
		
	    }
	    else if (num == 10) {
		System.out.println("Life Insurance matures - Collect $100");
		p.addMoney(100);
	    }
	    else if (num == 11) {
		System.out.println("Pay hospital fees of $100");
		p.addMoney(-100);
	    }
	    else if (num == 12) {
		System.out.println("Pay school fees of $150");
		p.addMoney(-150);
	    }
	    else if (num == 13) {
		System.out.println("Receive $25 consultancy fee");
		p.addMoney(25);
	    }
	    else if (num == 14) {
		System.out.println("You have won second prize in a beauty contest - Collect $10");
		p.addMoney(10);
	    }
	    else if (num == 15) {
		System.out.println("You inherit $100");
		p.addMoney(100);
	    }

	}
	
	//buyables:
	else {
	    if (!((Buyable)(p.position())).isOwned()) {
		if (((Buyable)(p.position())).buyDialogue()) {
		    p.buy((Buyable)(getBoard().getSpace(newPosInt)));
		    System.out.println("You are now the owner of " + getBoard().getSpace(newPosInt) + "!");
		}
		else {
		    System.out.println("That's Fine. I guess someone else will get it :(");
		}
	    }
	    else {
		Player owner = ((Buyable)(getBoard().getSpace(newPosInt))).owner();
		Buyable place = (Buyable)(getBoard().getSpace(newPosInt));
		System.out.println(getBoard().getSpace(newPosInt) + " is owned by " + owner.name());
		if (p == owner) {System.out.println("You own this property.");}
		else {
		    if (newPosInt % 5 == 0) { //railroads
			int rent = ((Railroad)(place)).rent(owner.railroadsOwned());
			System.out.println("The rent is " + rent);
			p.payMoney(rent, owner);
		    }
		    else if (newPosInt == 12 || newPosInt == 28) { //utilities
			int dice = dice1 + dice2;
			int rent = ((Utility)(place)).rent(dice);
			System.out.println("The rent is " + rent);
			p.payMoney(rent,owner);
		    }
		    else { //property
			int rent = ((Property)(place)).rent();
			System.out.println("The rent is " + rent);
			p.payMoney(rent, ((Buyable)(getBoard().getSpace(newPosInt))).owner());
		    }
		}
	    }
	}
	////
	System.out.println("You now have " + p.money());
	System.out.println("And own: " + listProperties(p));
	//buy houses or hotel  and promtp
	//update monopolies
	int[] monop = new int[8];
	boolean anyMonop = false;
	for (int i = 0; i < 8; i++)
	    if (p.checkMonopoly(i)) {
		p.monopolies()[i] = true;
		anyMonop = true;
	    }
	    else
		p.monopolies()[i] =false;
	
	
	if (anyMonop && wantHouses(s,p)) {
	    //buy houses:
	    ArrayList<Integer> ownedPropertyIds = new ArrayList<Integer>();
	    for (Buyable b: p.getOwned()) 
		if (b instanceof Property && ((Property)b).isMonopoly())
		    ownedPropertyIds.add(b.getIntPos());
	    
	    System.out.println("You can build houses on: " + listMonopolyProperties(ownedPropertyIds));
	    int id = monopolyPropertyId(s,p, ownedPropertyIds);
	    boolean buyHouse = yesOrNo(s,p, (Property)(getBoard().getSpace(id)));
	    //ACUAL HOUSE BUY:
	    if (buyHouse) {
		p.buyHouse((Property)(getBoard().getSpace(id)));
		System.out.println("You have bought a house on " + getBoard().getSpace(id));
		System.out.println("You now have " + p.money());
	    }
	    else {
		System.out.println("Maybe next time...");
	    }
	}
	
	if (p.money() <= 0) {
	    System.out.println("You don't have any money to spend. You're out of the game");
	    return;
	}
	
	System.out.print("Type anything to end your turn: ");
	s.next();
	
	
    }
    private static boolean yesOrNo(Scanner s, Player p, Property k) {
	System.out.println("Buying a house will cost you " + k.housePrice() + ".Proceed or no (y,n)?");
	String resp = s.next();
	if (resp.equalsIgnoreCase("y")) return true;
	else if (resp.equalsIgnoreCase("n")) return false;
	return yesOrNo(s,p,k);
    }
	
    private static String listMonopolyProperties(ArrayList<Integer> L) {
	String ans = "";
	for (int i = 0 ; i < L.size()-1;i++) 
	    ans += L.get(i) + ", ";
	ans += L.get(L.size()-1);
	return ans;
    }

    private static int monopolyPropertyId(Scanner s, Player p, ArrayList<Integer> idList) {
	
	System.out.print("Type the id of the property you want to build houses on: ");
	int id = s.nextInt();
	for (Integer x: idList) 
	    if (id == x) return id;
	return monopolyPropertyId(s,p,idList);
	
    }

    private static String listProperties(Player p) {
	String ans = "";
	for (Buyable b: p.getOwned()) {
	    ans += b + "(id: " + b.getIntPos() + ")" + "\n";
	}
	if (ans.equals("")) return "(nothing)";
	return ans;
    }
    
    private static boolean wantHouses(Scanner s, Player p) {
	System.out.print("Do you want to buy houses/hotels for your monopoly");
	String resp = s.next();
	if (resp.equalsIgnoreCase("y")) return true;
	if (resp.equalsIgnoreCase("n")) return false;
	else return wantHouses(s,p);
    }

    private static int inJailActions(Scanner s, Player p) {
	String response = s.next();
	String[] actions = {"DOUBLES", "FINE", "CARD"};
	for (int i = 0; i< actions.length; i++) {
	    if (response.equalsIgnoreCase(actions[i])) {
		if (i == 2 && p.numGetOutOfJailFreeCards() <= 0) {
		    System.out.print("You do not have Get Out of Jail Free Cards. Pick something else: ");
		    return inJailActions(s, p);
		}
		  
		return i;
	    }
	}
	System.out.print("That is not an option: ");
	return inJailActions(s, p);
    }
    
    private Player nextPlayer(int playerIndex) {
	playerIndex = (playerIndex + 1) % (getPlayers().size()); //cycle through players
	Player currPlayer = getPlayers().get(playerIndex);
	if (currPlayer.money() <= 0) return nextPlayer(playerIndex);
	return currPlayer;
    }
  
    private String winner() {
	for (Player p: getPlayers()) 
	    if (p.money() > 0) return p.name();
	return null;
    }
    
    public static void main(String[] args)  throws FileNotFoundException{
	MonopolyGame G = new MonopolyGame(); 
	Scanner s = new Scanner(System.in);

	int playerIndex = -1; 
    
	//debug:
	/*    for (Space a: G.getBoard().getBoard()) {
	      System.out.println(a.getIntPos());
	      }
	*/
	while (G.numPeopleWithMoney() != 1) {
	    System.out.print(RESET);
      
	    //update players
      
	    G.getMap().printMap(G.getPlayers());
	    System.out.println();
      
	    //internal crap
	    Player currPlayer = G.nextPlayer(playerIndex);
	    playerIndex++;
	    // add int details of prop money and monopolies'
	    System.out.println("It is " + currPlayer.name() + "'s turn.");
	    System.out.println("You have " + currPlayer.money() + " dollars.");
      
	    // space action:
	    G.playerTurn(s, currPlayer);
      
	    //end turn
	}
	System.out.println(G.winner() + " is the winner!!!!!!!!!!!!!!");
    
    }
  
}

