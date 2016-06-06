import java.util.*;
import java.io.*;

public class Game {

    // TODO: add static finals with coordinates of pos
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

    // c is a single char
    private void changeChar(int row, int col, String c){
	String toBeReplaced = boardLines.get(row);
	String newString = toBeReplaced.substring(col) + c + toBeReplaced.substring(col+1);
	boardLines.set(row,newString);
    }

    private void printMap() {
	for (String line : boardLines) 
	    System.out.println(line);
    }
    

    public static void main(String[] args) throws FileNotFoundException{
	Game G = new Game();
	System.out.println(G.boardLines);
	    //G.changeChar(0,0,"+");
	    //G.printMap();
    }

}
