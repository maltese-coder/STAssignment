package ModelBasedTesting;

import junit.framework.Assert;
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
        bronze = false,
        silver = false,
        gold = false;

    private double TotalCommision = 0;

    public AffiliateOperatorStates getState()
    {
        return modelAffiliate;
    }

    public void reset(final boolean b)
    {
        if(b)
        {
            systemUnderTest = new AffiliateOperator();
            TotalCommision = 0;
        }
        modelAffiliate = AffiliateOperatorStates.BRONZE;
    }

    public boolean addCommisionGuard() {
        return  getState() == AffiliateOperatorStates.BRONZE || getState() == AffiliateOperatorStates.SILVER || getState() == AffiliateOperatorStates.GOLD;
    }
    public @Action void addCommision()
    {
        systemUnderTest.increaseCommision();

        TotalCommision +=0.5;

        //updating model
        if(getState().equals(AffiliateOperatorStates.BRONZE) && TotalCommision <50.0)
        { // is in bronze but cannot go to silver yet
            bronze = true;

            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("The SUT's state does not match the affiliate model after still being bronze after increase in commision", bronze, systemUnderTest.isBronze());
        }
        else
        if(getState().equals(AffiliateOperatorStates.SILVER) && TotalCommision <500.0)
        {//is in silver but cannot go to gold yet

            silver = true;
            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("The SUT's state does not match the affiliate model after still being silver after increase in commision", silver, systemUnderTest.isSilver());
        }
        else if(getState().equals(AffiliateOperatorStates.BRONZE) && TotalCommision >=50.0)
        {//can be promoted to silver
            modelAffiliate = AffiliateOperatorStates.SILVER;
            bronze = false;
            silver = true;

            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("The SUT's state does not match the affiliate model after promoting to silver", silver, systemUnderTest.isSilver());
        }
        else
        if(getState().equals(AffiliateOperatorStates.SILVER) && TotalCommision >=500.0)
        {//can be promoted to gold
            modelAffiliate = AffiliateOperatorStates.GOLD;
            silver = false;
            gold = true;

            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("The SUT's state does not match the affiliate model after promoting to gold", gold, systemUnderTest.isGold());
        }

        //Checking correspondence between model and SUT.
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
