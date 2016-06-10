import java.util.*;
import java.io.*;

public class MapText {

    private static final String CLEAR = "0m";
    private static final String RED = "[41m";
    private static final String YELLOW = "[43m";
    private static final String GREEN = "[42m";
    private static final String BLUE = "[44m";
    private static final String GRAY = "[41m"; //
    private static final String CYAN = "[46m";
    private static final String MAGENTA = "[45m";
    private static final String BLACK = "[40m";
    

    // 6 PLAYERS
    // row,col
    private static final Integer[][] FIRST_PLAYER_POS =
    {
	{63,148},{63,132},{63,118},{63,104},{63,90},{63,76},{63,62},{63,48},{63,34},{63,20},
	{62,7},{56,5},{50,5},{45,5},{39,5},{33,5},{28,5},{22,5},{18,5}, {12,5}, //62,7:jail
	{5,5},{5,20},{5,34},{5,48},{5,62},{5,76},{5,90},{5,104},{5,118},{5,132}, //5,5:parking
	{5,148},{12,148},{18,148},{22,148},{28,148},{33,148},{37,148},{43,148},{50,148},{56,148} //5,148:gotojail
    };
    
    //row,col,length,id
    private static final Integer[][][][] BGRND_COLOR = 
    {};

    private ArrayList<ArrayList<Character>> boardLines;

    public MapText() throws FileNotFoundException{
	
	// import map.txt to boardlines
	boardLines = new ArrayList<ArrayList<Character>>();
	File txt = new File("map.txt");
	try {
	    Scanner sc = new Scanner(txt);
	    while (sc.hasNextLine())
		boardLines.add(charArrayList(sc.nextLine()));
	    
	} catch (FileNotFoundException ex) {}
	
    }

    private char changeChar(int row, int col, char c){
	char ans = boardLines.get(row).get(col);
	boardLines.get(row).set(col,c);
	return ans;
    }

    //TODO : add in color/player proc
    private void printMap() {
	for (ArrayList<Character> line : boardLines) {
	    String s = "";
	    for (Character c : line) s += c.toString();
	    System.out.println(s);
	}
    }
    
    private static ArrayList<Character> charArrayList(String s) {
	ArrayList<Character> chars = new ArrayList<Character>();
	for (char c : s.toCharArray()) chars.add(c);
	return chars;
    }

    public static void main(String[] args) throws FileNotFoundException{
	MapText G = new MapText();
	G.changeChar(0,0,'+');
	System.out.println(G.FIRST_PLAYER_POS.length);
	//G.printMap();
	
    }

}
