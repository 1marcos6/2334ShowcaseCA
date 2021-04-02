public class Driver
{
   public static void main(String [] args) throws InvalidRuleNumException, InvalidStepNumException {

       BoundaryConditions bc = new CircularBoundaryConditions();
       String gen = "............................................................O............................................................";


           Rule testr = new ElementaryRule(22);
           Generation testg = new Generation(gen);
           Automaton test = new Automaton(testr, testg, bc);
           test.getGeneration(501);
           System.out.println(test.getHistory());


   }
}
