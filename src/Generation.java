/**
 * The Generation class represents an array of Cells on one line at a certain time.
 */
public class Generation
{
    /**
     * Cell[] cells is an array representation of the current generation
     * of cells.
     */
    private Cell[] cells;

    /**
     * Constructor creates a new generation of cells based on
     * an array of states passed to the constructor.
     * @param states The array of CellStates passed to the constructor
     *               in order to create a new Generation.
     */
    public Generation(CellState[] states)
    {
        cells = new Cell[states.length];
        for(int i = 0; i<states.length; i++)
        {
            cells[i] = new Cell(states[i]);
        }
    }

    /**
     * Constructor makes a generation from a String of states.
     * @param states This string contains input such as ".O.O...O" which can be
     *               interpreted as individual ON and OFF cells. They are converted
     *               into cell format and placed into an internal array.
     */
    public Generation(String states)
    {
        cells = new Cell[states.length()];
        for(int i = 0; i<states.length();i++)
        {
            if(states.charAt(i) == '.' || states.charAt(i) == 'O')
            {
                cells[i] = new Cell(CellState.getState(states.charAt(i)));
            }
            else
            {
                throw new IllegalArgumentException();
            }

        }
    }

    /**
     * Constructor creates a Generation from a predetermined Cell array.
     * @param cells A cell array which is directly copied to create the internal array
     *              of the specific Generation instance.
     */
    public Generation(Cell[] cells)
    {
        this.cells = new Cell[cells.length];
        for(int i = 0; i<cells.length; i++)
        {
            this.cells[i] = cells[i];
        }
    }

    /**
     * Size() returns the size of the generation.
     * @return The integer length of the Cell array, or the Generation.
     */
    public int size()
    {
        return cells.length;
    }

    /**
     * getCell(idx) returns a certain cell in the Generation.
     * @param idx The index of the desired Cell to be returned.
     * @return The cell at the index specified.
     */
    public Cell getCell(int idx)
    {
        return cells[idx];
    }

    /**
     * toString returns a String representation of the entire Generation.
     * @return Returns the String representation of the current Generation specified.
     */
    public String toString()
    {
        String returned = "";
        for(int i = 0; i<cells.length; i++)
        {
            returned += cells[i].toString();
        }
        return returned;
    }
}
