/**
 * Cell class represents an individual cell and works alongside the CellState enumeration
 * to hold the state of each cell, either ON or OFF, as denoted by the character symbols.
 */
public class Cell
{
    /**
     * State holds the CellState of each Cell class, either ON or OFF.
     */
    private CellState state;

    /**
     * Default constructor for the Cell class, automatically
     * assigns OFF to CellState.
     */
    public Cell()
    {
        this.state = CellState.OFF;
    }

    /**
     * Constructor for the Cell class that sets the state of the Cell
     * as either ON or OFF.
     * @param state The state passed to the constructor and in turn
     *              applied to the current iteration of cell.
     */
    public Cell(CellState state)
    {
        this.state = state;
    }

    /**
     * Returns the current state of a cell.
     * @return The current state of the cell is returned.
     */
    public CellState getState()
    {
        return state;
    }

    /**
     * Returns the String representation of the Cell's state, represented by
     * a symbol of either "." for OFF or "O" for ON.
     * @return The character symbol representing the state of a Cell.
     */
    public String toString()
    {
        return state.toString();
    }

}
