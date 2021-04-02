import java.util.LinkedList;
import java.util.List;

/**
 * This class contains a linked list of generations, and so this class is the culmination of all evolutions of an initial generation plus that generation.
 */
public class Automaton
{
    /**
     * Private rule object in order to use the functions of the Rule class.
     */
    private Rule rule;
    /**
     * A list of all the generations up until a certain point.
     */
    private List<Generation> generations;
    /**
     * The conditions that a cell must follow to evolve.
     */
    private BoundaryConditions bc;

    /**
     * The constructor instantiates the local Rule and boundary conditions, along with the generations list, while adding the initial generation to that list.
     * @param rule The rule object passed to the constructor.
     * @param init The initial generation of cells.
     * @param bc The boundary conditions that cells must follow to evolve.
     */
    public Automaton(Rule rule, Generation init, BoundaryConditions bc)
    {
        this.rule = rule;
        this.bc = bc;
        generations = new LinkedList<>();
        generations.add(init);
    }

    /**
     * Returns the rule object.
     * @return The rule object.
     */
    public Rule getRule()
    {
        return rule;
    }

    /**
     * This retrieves a certain generation of the Automaton, and evolves the Automaton to that point if it has not yet been reached.
     * @param stepNum The certain step of an Automaton being seeked.
     * @return The generation requested.
     */
    public Generation getGeneration(int stepNum) throws InvalidStepNumException {

        if(stepNum<0)
        {
            throw new InvalidStepNumException();
        }
        if(generations.size() - 1 < stepNum)
        {
            int i = stepNum - getTotalSteps();
            evolve(i);
            return generations.get(getTotalSteps());
        }
        return generations.get(stepNum);

    }

    /**
     * Gets the current boundary conditions.
     * @return The set boundary conditions.
     */
    public BoundaryConditions getBoundaryConditions()
    {
        return bc;
    }

    public void evolve(int numSteps) throws InvalidStepNumException
    {
        if (numSteps < 0)
        {
            throw new InvalidStepNumException();
        }
        else
            {
            int count = 0;
            while (count < numSteps)
            {
                generations.add(rule.evolve(generations.get(generations.size() - 1), bc));
                count++;
            }
        }
    }

    /**
     * Returns the total amount of steps the Automaton has evolved.
     * @return The total amount of steps the Automaton has evolved.
     */
    public int getTotalSteps()
    {                                               //todo: MAYBE BROKE

        return generations.size()-1;
        //returns the length of the linked list minus 1 because initial gen is not an evolution
    }

    /**
     * Creates a String interpretation of the most recent Generation.
     * @return The most recent Generation, in String form.
     */
    public String toString()
    {
        return generations.get(generations.size()-1).toString();
    }

    /**
     * Creates a String interpretation of the entire Automaton.
     * @return The entire Automaton, in String form.
     */
    public String getHistory()
    {
        String automaton = "";
        for(int i = 0; i<generations.size(); i++)
        {
            if(i<generations.size()-1)
            {
                automaton = automaton + generations.get(i).toString() + '\n';
            }
            else
            {
                automaton = automaton + generations.get(i).toString();
            }
        }
        return automaton;
    }




}
