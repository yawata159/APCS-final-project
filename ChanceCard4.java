public class ChanceCard4 implements ChanceCard{

    private final int _id=4;
    private final String _text = "Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.";

    public int getId(){
      return _id;
    }

    public void action(Player p){
        if (p.getIntPosition().CompareTo(5) < 0 && p.getIntPosition().CompareTo(35) > 0 )
            p.setPosition(board.getSpace(5));
        else if (p.getIntPosition().CompareTo(15) < 0)
            p.setPosition(board.getSpace(15));
        else if (p.getIntPosition().CompareTo(25) < 0)
            p.setPosition(board.getSpace(25));
        else
            p.setPosition(board.getSpace(35));
        if (!p.getPosition().isOwned())
            p.buy();
        //else  pay twice the rent
    }
}
