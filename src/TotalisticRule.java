import java.util.HashMap;

/**
 * This class contains the functions for a one dimensional totalistic cellular automaton with a neighborhood radius of 2.
 */
public class TotalisticRule extends Rule {


    /**
     * This custom HashMap contains the standard stationary subrule numbers (sum of ON states, in descending order from 5 to 0) as keys, and the values unlocked
     * by the keys are the corresponding EvolvedCell state, based on what rule number is applied.
     */
    private HashMap<Integer, CellState> rulez;

    /**
     * Constructor for a TotalisticRule utilizes a private static method in order to check the validity of the rule number
     * passed to the constructor, and also generates a HashMap that contains the instructions for the specific rule number.
     * @param ruleNum Totalistic rule number.
     * @throws InvalidRuleNumException Exception thrown when a rule number parameter is less than 0 or greater than 63. Such numbers are invalid.
     */
    public TotalisticRule(int ruleNum) throws InvalidRuleNumException {
        super(exceptionThrower(ruleNum));
        rulez = genRuleSet(ruleNum);
    }

    /**
     * A getter method for the number of subrules. There are always a set of 6 subrules for totalistic rules.
     * @return The number of subrules.
     */
    @Override
    public int getNumSubrules() {
        return 6;           //Number of subrules for totalistic, CONSTANT
    }

    /**
     * Retrieves a neighborhood of a certain cell in a given Generation.
     * @param cellIdx Index of the cell that serves as the center of the neighborhood.
     * @param gen Generation where the cells are located.
     * @param bc The boundary constraints applied to this search in the case of any extremities (eg celLIdx being 0)
     * @return Returns a Cell array of length 5 that contains the original cellIdx cell at idx 2, and its neighbors to the left and right.
     */
    @Override
    public Cell[] getNeighborhood(int cellIdx, Generation gen, BoundaryConditions bc) {
        Cell[] neighborhood = new Cell[5];
        neighborhood[2] = gen.getCell(cellIdx);
        neighborhood[1] = bc.getNeighbor(cellIdx, -1, gen);
        neighborhood[0] = bc.getNeighbor(cellIdx, -2, gen);
        neighborhood[3] = bc.getNeighbor(cellIdx, 1, gen);
        neighborhood[4] = bc.getNeighbor(cellIdx, 2, gen);
        return  neighborhood;
    }

    /**
     * Evolves a certain cell in the center of a given neighborhood.
     * @param neighborhood The neighborhood that determines the evolution of a certain cell.
     * @return An EvolvedCell Cell that has been determined by the neighborhood given.
     */
    @Override
    public EvolvedCell evolve(Cell[] neighborhood) {
        int onCount = 0;
        for(int i=0; i<neighborhood.length; i++)
        {
            if(neighborhood[i].getState() == CellState.ON)
            {
                onCount++;
            }
        }
        return new EvolvedCell(rulez.get(onCount), onCount);
    }

    /**
     * Returns the ruleSet in String form.
     * @return the ruleSet in String form.
     */
    @Override
    public String toString()
    {
        String returned = "5 4 3 2 1 0\n";
        for(int i = 5; i>=0; i--)
        {
            if(i!=0)
            {
                returned = returned + rulez.get(i).toString() + " ";
            }
            else
            {
                returned = returned + rulez.get(i).toString();
            }

        }
        return returned;
    }

    private HashMap<Integer, CellState> genRuleSet(int ruleNum)
    {
        String binary =  Integer.toBinaryString(ruleNum);
        if(binary.length() != 6)
        {
            int leadingZeroes = 6 - binary.length();
            for(int i = 0; i<leadingZeroes; i++)
            {
                binary = "0" + binary;
            }
        }
        CellState[] ruleSet = new CellState[6];
        for(int i = 0; i< ruleSet.length; i++)
        {
            if(binary.charAt(i) == '0')
            {
                ruleSet[i] = CellState.OFF;
            }
            else
            {
                ruleSet[i] = CellState.ON;
            }
        }
        HashMap<Integer, CellState> rulez = new HashMap<>();
        rulez.put(5, ruleSet[0]);
        rulez.put(4, ruleSet[1]);
        rulez.put(3, ruleSet[2]);
        rulez.put(2, ruleSet[3]);
        rulez.put(1, ruleSet[4]);
        rulez.put(0, ruleSet[5]);
        return rulez;
    }

    private static int exceptionThrower(int ruleNum) throws InvalidRuleNumException {
        if(ruleNum<0 || ruleNum>63)
        {
            throw new InvalidRuleNumException();
        }
        else
        {
            return ruleNum;
        }
    }
}
