package ModelBasedTesting;

import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;

import java.util.Random;

/**
 * Created by Matthew on 17-Dec-16.
 */
public class AffiliateOperatorModel implements FsmModel
{
    //Linking the SUT
    private AffiliateOperator systemUnderTest = new AffiliateOperator();

    //State Variables
    private AffiliateOperatorStates modelAffiliate = AffiliateOperatorStates.BRONZE;
    private boolean
        bronze = true,
        silver = false,
        gold = false;

    private double TotalBalance = 0;
    private double CurrentBalance = 0;

    public AffiliateOperatorStates getState()
    {
        return modelAffiliate;
    }

    public boolean checkWithdraw()
    {
        if(CurrentBalance >= 5)
            return true;
        else
            return false;
    }

    public void reset(final boolean b)
    {
        //reset SUT
        if(b)
        {
            systemUnderTest = new AffiliateOperator();
        }

        //reset test model
        bronze = true;
        silver = false;
        gold = false;
        TotalBalance = 0;
        CurrentBalance = 0;
        modelAffiliate = AffiliateOperatorStates.BRONZE;
    }

    public boolean withdrawBalanceGuard(){return checkWithdraw();}
    public @Action void withdrawBalance()
    {
        systemUnderTest.withdrawCurrBalance();

        CurrentBalance = 0;

       // org.junit.Assert.assertEquals("The SUT's balance does not match the balance of the model after withdrawing commision not yet collected", 0, systemUnderTest.getCurrentBalance());
        org.junit.Assert.assertEquals(0,systemUnderTest.getCurrentBalance(),0.001);
    }

    public boolean adClickGuard() {
        return getState() == AffiliateOperatorStates.BRONZE || getState() == AffiliateOperatorStates.SILVER || getState() == AffiliateOperatorStates.GOLD;
    }
    public @Action void adClick()
    {
        systemUnderTest.increaseBalance();

        TotalBalance +=0.5;
        CurrentBalance +=0.5;

        //updating model
        if(TotalBalance <50.0)
        { // is in bronze but cannot go to silver yet

            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("The SUT's state does not match the affiliate model after still being BRONZE after increase in balance", bronze, systemUnderTest.isBronze());
        }
        else if(TotalBalance ==50.0)
        {//can be promoted to silver
            modelAffiliate = AffiliateOperatorStates.SILVER;
            bronze = false;
            silver = true;

            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("The SUT's state does not match the affiliate model after promoting to SILVER", silver, systemUnderTest.isSilver());
        }
        else
        if(TotalBalance <500.0)
        {//is in silver but cannot go to gold yet
            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("The SUT's state does not match the affiliate model after still being SILVER after increase in balance", silver, systemUnderTest.isSilver());
        }
        else
        if(TotalBalance ==500.0)
        {//can be promoted to gold
            modelAffiliate = AffiliateOperatorStates.GOLD;
            silver = false;
            gold = true;

            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("The SUT's state does not match the affiliate model after promoting to GOLD", gold, systemUnderTest.isGold());
        }
        else
        {   //is in gold therefore cannot go further
            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("The SUT's state does not match the affiliate model after still being GOLD after increase in balance", gold, systemUnderTest.isGold());
        }

        //Checking correspondence between model and SUT.
        org.junit.Assert.assertEquals(TotalBalance,systemUnderTest.getTotalBalance(),0.001);
        org.junit.Assert.assertEquals("SUT has multiple states values at once.", false, systemUnderTest.getInactiveStates());
    }

    @Test
    public void main() {
        final RandomTester tester = new GreedyTester(new AffiliateOperatorModel()); //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
        tester.setResetProbability(0.001); // long test sequences
        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        tester.buildGraph(); //Builds a model of our FSM to ensure that the coverage metrics are correct.
        tester.addListener(new StopOnFailureListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.
        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have ben executed during the execution of the test.
        tester.generate(5000); //Generates 500 transitions
        tester.printCoverage(); //Prints the coverage metrics specified above.
    }
}
