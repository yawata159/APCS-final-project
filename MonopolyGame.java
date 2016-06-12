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
	    System.out.println("Type name of this player (or write START to start playing):");
	    String name=s.next();
	    if (name.equals("START"))  {
		if (_players.size() == 1) System.out.println("Not enough Players");
		else break;
	    }
	    else {
		_players.add(new Player(name, 1500, this));
	    }
	}
	s.close();
	_board=new MonopolyBoard();
	//_map=new MapText();
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
	
