/**
 * The EvolvedCell class represents a Cell that has been produced
 * by a generation of an earlier set of Cells.
 */
public class EvolvedCell extends Cell
{

    /**
     * The subrule number, which is the base-10 representation of a certain rule set.
     */
    private int subruleNum;

    /**
     * Constructor for the Evolved Cell, includes the current state of
     * the cell(ON, or OFF) and the subrule number of the cell.
     * @param state The current state of the evolved cell, passed to the
     *              iteration of the class.
     * @param subruleNum The current subrule of the Cell.
     */
    public EvolvedCell(CellState state, int subruleNum)
    {
        super(state);
        this.subruleNum = subruleNum;
    }

    /**
     * The method returns the subrule number of the cell.
     * @return The current subrule of the cell.
     */
    public int getSubruleNum()
    {
        return subruleNum;
    }
}
