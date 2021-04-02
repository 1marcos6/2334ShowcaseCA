public class AutomatonMeasurements {
    public static int hammingDistance(Generation g1, Generation g2) {
        if (g1.size() != g2.size()) {
            throw new IllegalArgumentException();
        }

        int differencecount = 0;
        for (int i = 0; i < g1.size(); i++) {
            if (!(g1.getCell(i).getState().equals(g2.getCell(i).getState()))) {
                differencecount++;
            }
        }
        return differencecount;
    }

    public static int hammingDistance(int stepNum, Automaton a) throws InvalidStepNumException {
        if (stepNum < 0) {
            throw new InvalidStepNumException();
        } else {
            return (hammingDistance(a.getGeneration(stepNum - 1), a.getGeneration(stepNum)));
        }
    }


    public static int[] hammingDistances(Automaton a) throws InvalidStepNumException {
        int[] returned = new int[a.getTotalSteps()];

        for (int i = 1; i <= returned.length; i++) {
            returned[i - 1] = hammingDistance(i, a);
        }
        return returned;
    }

    public static int[] subruleCount(int stepNum, Automaton a) throws InvalidStepNumException
    {
        Cell[] neighborhood;
        if(stepNum<0)
        {
            throw new IllegalArgumentException();
        }
        int[] returnedCount = new int[a.getRule().getNumSubrules()];
        int count = 0;
        for(int i = 0; i< a.getGeneration(stepNum-1).size(); i++)
        {
            neighborhood = a.getRule().getNeighborhood(i,a.getGeneration(stepNum-1),a.getBoundaryConditions());
            int tempsubRule = a.getRule().evolve(neighborhood).getSubruleNum();
            for(int j = 0; j< a.getGeneration(stepNum-1).size(); j++)
            {
                neighborhood = a.getRule().getNeighborhood(j, a.getGeneration(stepNum-1),a.getBoundaryConditions());
                int subRuleNumber = a.getRule().evolve(neighborhood).getSubruleNum();
                if(subRuleNumber == tempsubRule)
                {
                    count++;
                }

            }
            returnedCount[tempsubRule] = count;
            count = 0;
        }
        return returnedCount;
    }



    public static int[][] subruleCounts(Automaton a) throws InvalidStepNumException {
        int colCount = a.getRule().getNumSubrules();
        int[][] subruleCounts = new int[a.getTotalSteps()][colCount];
        int[] genCount;
        int i = 1;
        int secondIdxCount = 0;
        while (i <= a.getTotalSteps()) {
            for (int j = 0; j < colCount; j++) {
                genCount = AutomatonMeasurements.subruleCount(i, a);
                subruleCounts[secondIdxCount][j] = genCount[j];
            }
            i++;
            secondIdxCount++;
        }
        return subruleCounts;
    }


}



