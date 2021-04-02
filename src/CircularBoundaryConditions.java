/**
 * This boundary condition implements a clock shaped mathematical relationship when it comes to neighbors.
 */
public class CircularBoundaryConditions implements BoundaryConditions {
    /**
     * This retrieves the neighbors of a cell when normally possible, and does clockwise computations when not normally possible.
     *
     * @param cellIdx the index of the Cell
     * @param offset  the index of the neighbor relative to the Cell
     * @param gen     the Generation
     * @return The certain neighbors of a cell in a generation.
     */
    @Override
    public Cell getNeighbor(int cellIdx, int offset, Generation gen) {

        //a new and more cleaner way of implementing this, explained to me by marc hanaa
        int difference = cellIdx + offset;
        int newIdx = (difference % gen.size());
        if(newIdx ==0)
        {
            return gen.getCell(0);
        }
        if(difference > gen.size() -1)
        {
            return gen.getCell(newIdx);                     //passes all test cases.
        }
        else if(difference < 0)
        {
            return gen.getCell(newIdx+(gen.size()));
        }
        return gen.getCell(difference);
    }
}
