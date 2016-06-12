import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MonopolyGame{

    private static final String RESET = "[2J[1;1H";

    public ArrayList<Player> _players;
    public MonopolyBoard _board;
    //public MapText _map;
    
    /*
      ...
    */
    // for _map: internal stuff, placeplayers(maptext), printboard(maptext), clearplayers(maptext)
    public MonopolyGame(){
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
	//_map=new MapText();
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
    
    
    public MonopolyBoard getBoard(){
	return _board;
    }

    public static void main(String[] args) {
	MonopolyGame G = new MonopolyGame(); 
	// take turn
	// maptext :clearmap
	// maptext :placeplayers
	// maptext :printboard
	// maptext :clearplayers
    }

}
	
