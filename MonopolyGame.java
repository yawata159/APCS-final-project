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
    System.out.println("Monopoly! For up to 6 players");
    System.out.println("Make sure the terminal is zoomed out enough for the board to fit");
    while (_players.size() < 6){
      System.out.print("Type name of this player(or write START to start playing): ");
      String name=s.next();
      
      if (name.equalsIgnoreCase("START"))  {
        if (_players.size() == 1) System.out.println("Not enough Players");
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
  
  
  public boolean isThereAWinner() {
    int sum = 0;
    for (Player p : _players) {
      if (p.money() <= 0) sum += 0;
      else sum +=1;
      
      if (sum > 1) return false; //still more than one person playing
    }
    return (sum == 1);
    
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
		      
		  }
		  else {
		      System.out.println("Those aren't doubles. Wait another turn");
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
		  p.exitJail();
	      }
	      else {
		  System.out.println("You don't have any get out of jail cards.");
		  return;
	      }
	  }
	}
	//else
     
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
      int newPosInt = (p.position().getIntPos() + dice1 +dice2) % 40;
      Space newPos = getBoard().getSpace(newPosInt); 
      p.setPosition(newPos);
      System.out.println("You landed on " + newPos.getName());
      //nonjail spaces:
      if (newPosInt == 4) { //income tax
	  int x=p.money();
	  int tax=Math.min(200,(x/10));
	  p.addMoney((-1)*tax);
      }
      
      System.out.print("Type anything to end your turn: ");
      s.next();
      

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
    
  
  public static void main(String[] args)  throws FileNotFoundException{
    MonopolyGame G = new MonopolyGame(); 
    Scanner s = new Scanner(System.in);

    int playerIndex = -1; 
    
    while (!G.isThereAWinner()) {
      System.out.print(RESET);
      
      //display crap
      G.getMap().printMap();
      System.out.println();
      
      //internal crap
      playerIndex = (playerIndex + 1) % (G.getPlayers().size()); //cycle through players
      Player currPlayer = G.getPlayers().get(playerIndex);
      System.out.println("It is " + currPlayer.name() + "'s turn.");
      
      // space action:
      G.playerTurn(s, currPlayer);
      
      //end turn
    }
    
  }
  
}

