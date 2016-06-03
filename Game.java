import java.util.*;
import java.io.*;

public class Game {


    private ArrayList<String> boardLines;
    
    public Game() throws FileNotFoundException{
	
	// import map.txt to boardlines
	boardLines = new ArrayList<String>();
	File txt = new File("map.txt");
	try {
	    Scanner sc = new Scanner(txt);
	    while (sc.hasNextLine())
		boardLines.add(sc.nextLine());
	    
	} catch (FileNotFoundException ex) {}
	
    }

    private void printMap() {
	for (String line : boardLines) 
	    System.out.println(line);
    }
    

    public static void main(String[] args) throws FileNotFoundException{
	Game G = new Game();
	G.printMap();
    }

}
