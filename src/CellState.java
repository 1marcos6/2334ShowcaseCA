import java.util.HashMap;
import java.util.Map;

/**
The CellState enumeration is responsible for holding the individual state of every cell
 in the program. It represents ON and OFF with two character symbols that plug into a Map
 to get the values.
 */
public enum CellState {
    ON('O'),       
    OFF('.');
    /**
     * This char holds the character representation of an ON or OFF cell state.
     */
    private char symbol;
    /**
     * The SYMBOL_TO_STATE Map makes sure the character representations of a cell state are
     * also associated with their ON or OFF states.
     */
    private static final Map<Character, CellState> SYMBOL_TO_STATE = new HashMap<>();
        static {
            SYMBOL_TO_STATE.put('.', CellState.OFF);
            SYMBOL_TO_STATE.put('O', CellState.ON);
        }

    /**
     *      This constructor passes a symbol char to the stored symbol char in the enumeration.
     * @param symbol The symbol character stores the character representation of a cell state. This
     *               is saved within the iterated enumeration.
     */
    private CellState(char symbol)
    {
        this.symbol = symbol;
    }

    /**
     *  This method returns the current state of the selected cell by requiring a character
     *  symbol that either represents ON or OFF.
     * @param symbol Symbol is a character that represents ON or OFF that is passed
     *               to the method in order to return either ON or OFF.
     * @return Returns the currently stored state of the selected cell.
     */
    public static CellState getState(char symbol)
    {
        return SYMBOL_TO_STATE.get(symbol);
    }

    /**
     * Returns a String representation of the ON or OFF symbol.
     * @return Returned is a String of the ON or OFF symbol used.
     */
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
