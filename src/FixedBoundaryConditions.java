public class FixedBoundaryConditions implements BoundaryConditions {

    private CellState left;         //all cells at idx < 0 are deemed " "
    private CellState right;        //all cells at idx >= gen.size() are deemed " "

    public FixedBoundaryConditions(CellState left, CellState right)
    {
        this.left = left;
        this.right = right;
    }
    @Override
    public Cell getNeighbor(int cellIdx, int offset, Generation gen) {
        if(cellIdx+offset < 0)
        {
            if(left == CellState.OFF)
            {
                return new Cell(CellState.OFF);
            }
            else
            {
                return new Cell(CellState.ON);
            }

        }
        else if(cellIdx+offset >= gen.size())
        {
            if(right == CellState.OFF)
            {
                return new Cell(CellState.OFF);
            }
            else
            {
                return new Cell(CellState.ON);
            }
        }
        else
        {
            return gen.getCell(cellIdx+offset);
        }
    }

    public CellState getLeftState()
    {
        return left;
    }

    public CellState getRightState()
    {
        return right;
    }


}
