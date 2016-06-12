
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
	Scanner s=new Scanner(System.in);
	System.out.print(RESET);
	System.out.println("Monopoly! For up to 6 players");
	while (_players.size() <= 6){
	    System.out.print("Type name of this player(or write START to start playing): ");
	    String name=s.next();
	    
	    if (name.equals("START"))  {
		if (_players.size() == 1) System.out.println("Not enough Players");
		else break;
	    }
	    
	    else {
		System.out.print("Type the avatar you want to use for this player(ONE character): ");
		char av = getChar(s);
		_players.add(new Player(name, 1500, av, this));
	    }
	}
	s.close();
	_board=new MonopolyBoard();
	_map=new MapText();
    }
    
    private static char getChar(Scanner s) {
	String str = s.next();
	if (str.length() != 1) {
	    System.out.println("I said one character: ");
	    return getChar(s);
	}
	else 
	    return str.charAt(0);
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

    public ArrayList<Player> getPlayers() {
	return _players;
    }

    public static void main(String[] args)  throws FileNotFoundException{
	MonopolyGame G = new MonopolyGame(); 

	//set player positions to go
	for (int i = 0 ; i < G.getPlayers().size(); i++) {
	    G.getPlayers().get(i).setPosition(G.getBoard().getSpace(0)); 
	}
	
	int playerIndex = -1; 

	while (!G.isThereAWinner()) {
	    playerIndex = (playerIndex + 1) % (G.getPlayers().size()); //cycle through players
	    Player currPlayer = G.getPlayers().get(playerIndex);
	    int diceRoll = (int)(Math.random()*6) + (int)(Math.random()*6);
	    
	    // take turn
	    // maptext :clearmap
	    // maptext :placeplayers
	    // maptext :printboard
	    // maptext :clearplayers
	    
	}
	
    }

}
	
