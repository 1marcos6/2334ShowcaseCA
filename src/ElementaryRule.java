/**
 * The ElementaryRule class stores all of the workings of the implementation
 * of an Elementary evolution of a Cell and it's neighbors.
 */
public class ElementaryRule extends Rule {

    /**
     * A CellState array that contains the ON and OFF values of the current rule being considered.
     */
    private CellState[] ruleSet;
    /**
     * Constructor for ElementaryRule receives a rule number and localizes it for the class.
     * The constructor also generates a CellState array from the rule number given, made possible
     * by a custom helper method.
     *
     * @param ruleNum Rule number passed to the constructor.
     */
    public ElementaryRule(int ruleNum) throws InvalidRuleNumException {
        super(exceptionThrower(ruleNum));
        ruleSet = genRuleSet(ruleNum);
    }

    /**
     * Returns the number of subrules considered by this rule.
     * @return Always 8, as the Elementary rule table length is 8.
     */
    @Override
    public int getNumSubrules() {
        return 8;   //this should be correct as it is the number of subrules
                    //in an elementary rule table.
    }

    /**
     * This method retrieves a neighborhood by simply checking the array inside the given Generation.
     * @param cellIdx The middle of the 3 cell neighborhood.
     * @param gen The current generation.
     * @return The 3 cell neighborhood created.
     */
    @Override
    public Cell[] getNeighborhood(int cellIdx, Generation gen, BoundaryConditions bc) {
        Cell[] neighborhood = new Cell[3];
        neighborhood[1] = gen.getCell(cellIdx);
        neighborhood[0] = bc.getNeighbor(cellIdx, -1, gen);
        neighborhood[2] = bc.getNeighbor(cellIdx, 1, gen);
        return neighborhood;
    }

    /**
     * Evolves a cell by checking its neighborhood, and returns the evolved result.
     * @param neighborhood An array of 3 cells.
     * @return An evolved cell, depending on the neighborhood.
     */
    @Override
    public EvolvedCell evolve(Cell[] neighborhood) {
        if(neighborhood[0].getState() == CellState.ON && neighborhood[1].getState() == CellState.ON && neighborhood[2].getState() == CellState.ON)
        {
            //subrule 7, counts down from here
            return new EvolvedCell(ruleSet[0], 7);
        }
        else if(neighborhood[0].getState() == CellState.ON && neighborhood[1].getState() == CellState.ON && neighborhood[2].getState() == CellState.OFF)
        {
            return new EvolvedCell(ruleSet[1], 6);
        }
        else if(neighborhood[0].getState() == CellState.ON && neighborhood[1].getState() == CellState.OFF && neighborhood[2].getState() == CellState.ON)
        {
            return new EvolvedCell(ruleSet[2], 5);
        }
        else if(neighborhood[0].getState() == CellState.ON && neighborhood[1].getState() == CellState.OFF && neighborhood[2].getState() == CellState.OFF)
        {
            return new EvolvedCell(ruleSet[3], 4);
        }
        else if(neighborhood[0].getState() == CellState.OFF && neighborhood[1].getState() == CellState.ON && neighborhood[2].getState() == CellState.ON)
        {
            return new EvolvedCell(ruleSet[4], 3);
        }
        else if(neighborhood[0].getState() == CellState.OFF && neighborhood[1].getState() == CellState.ON && neighborhood[2].getState() == CellState.OFF)
        {
            return new EvolvedCell(ruleSet[5], 2);
        }
        else if(neighborhood[0].getState() == CellState.OFF && neighborhood[1].getState() == CellState.OFF && neighborhood[2].getState() == CellState.ON)
        {
            return new EvolvedCell(ruleSet[6], 1);
        }
        else
        {
            return new EvolvedCell(ruleSet[7], 0);
        }

    }

    /**
     * Returns a String representation of a default elementary rule set, with the corresponding rules below them.
     * @return A rule set and it's corresponding rules.
     */
    @Override
    public String toString() {
        String returned = "OOO OO. O.O O.. .OO .O. ..O ...\n" + " " + ruleSet[0].toString();
        for (int i = 1; i<ruleSet.length; i++)
        {
            returned = returned + "   " + ruleSet[i].toString();
        }
        returned = returned + " ";
        return returned;
    }

    /**
     * Custom helper method that generates a CellState array that corresponds to a certain rule number.
     * @param ruleNum Rule number passed to the constructor.
     * @return A CellState array that acts as an ON OFF rule set.
     */
    private CellState[] genRuleSet(int ruleNum)
    {
        String binary =  Integer.toBinaryString(ruleNum);
        if(binary.length() != 8)
        {
            int leadingZeroes = 8 - binary.length();
            for(int i = 0; i<leadingZeroes; i++)
            {
                binary = "0" + binary;
            }
        }
        CellState[] ruleSet = new CellState[8];
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
        return ruleSet;
    }

    /**
     * Method that enables a ruleNumber to be checked for validity.
     * @param ruleNum A ruleNumber that has been passed to the constructor, and now must
     *                be checked for validity.
     * @return If a rule number is valid, it will be returned.
     * @throws InvalidRuleNumException If a rule number is impossible, this exception will be thrown.
     */
    private static int exceptionThrower(int ruleNum) throws InvalidRuleNumException {
        if(ruleNum<0 || ruleNum>255)
        {
            throw new InvalidRuleNumException();
        }
        else
        {
            return ruleNum;
        }
    }
}
