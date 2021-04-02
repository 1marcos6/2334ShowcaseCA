/**
 * The Rule class contains the current Rule number of the generations being performed. It also contains options to evolve
 * a certain Generation based on the rule number saved.
 */
public abstract class Rule
{
    /**
     * The current cellular automaton rule number.
     */
    private int ruleNum;

    /**
     * Constructor for Rule receives a rule number and localizes it for the class.
     * @param ruleNum Rule number passed to the constructor.
     */
    protected Rule(int ruleNum)
    {
        this.ruleNum = ruleNum;
    }

    /**
     * This method returns the current rule number.
     * @return The rule number currently being utilized.
     */
    public int getRuleNum()
    {
        return ruleNum;
    }

    public Generation evolve(Generation gen, BoundaryConditions bc)
    {
        Cell[] arr = new EvolvedCell[gen.size()];
        for(int i = 0; i< gen.size(); i++)
        {
            arr[i] = evolve(getNeighborhood(i,gen,bc));
        }
        return new Generation(arr);
    }

    public abstract int getNumSubrules();

    public abstract Cell[] getNeighborhood(int cellIdx, Generation gen, BoundaryConditions bc);

    public abstract EvolvedCell evolve(Cell[] neighborhood);

    public abstract String toString();
}
