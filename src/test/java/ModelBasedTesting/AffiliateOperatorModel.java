package ModelBasedTesting;

import enums.AffiliateType;
import impl.AdPlatform;
import impl.Affiliate;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.*;
import org.junit.Test;

import java.util.Random;

/**
 * Created by Matthew on 17-Dec-16.
 */
public class AffiliateOperatorModel implements FsmModel
{
    //Linking the SUT
    private AdPlatform systemUnderTest = new AdPlatform();
    private Affiliate tempAffiliate;

    //State Variables
    private AffiliateOperatorStates modelAffiliate = AffiliateOperatorStates.UNREGISTERED;
    private boolean
        bronze = false,
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
            systemUnderTest = new AdPlatform();
            tempAffiliate = null;
        }

        //TODO: Outside or inside ??
        //reset test model
        bronze = false;
        silver = false;
        gold = false;
        TotalBalance = 0;
        CurrentBalance = 0;
        modelAffiliate = AffiliateOperatorStates.UNREGISTERED;

    }

    public boolean signUpGuard(){
        return getState() == AffiliateOperatorStates.UNREGISTERED;
    }
    public @Action void signUp()
    {
        tempAffiliate = new Affiliate(1,"Matthew");

        systemUnderTest.registerAffiliate(tempAffiliate);
        modelAffiliate = AffiliateOperatorStates.BRONZE;

        bronze = true;

        // org.junit.Assert.assertEquals("The SUT's balance does not match the balance of the model after withdrawing commision not yet collected", 0, systemUnderTest.getCurrentBalance());
        //org.junit.Assert.assertEquals(0,systemUnderTest.getCurrentBalance(),0.001);

        org.junit.Assert.assertEquals("The SUT's state does not match the affiliate model after signing up and going to BRONZE", bronze, tempAffiliate.getType() == AffiliateType.BRONZE);

    }

    public boolean settleBalanceGuard(){return (checkWithdraw() && (getState() == AffiliateOperatorStates.BRONZE ||
            getState() == AffiliateOperatorStates.SILVER || getState() == AffiliateOperatorStates.GOLD));}
    public @Action void settleBalance()
    {
        systemUnderTest.settleAffiliateBalance(tempAffiliate);

        CurrentBalance = 0;

       // org.junit.Assert.assertEquals("The SUT's balance does not match the balance of the model after withdrawing commision not yet collected", 0, systemUnderTest.getCurrentBalance());
        org.junit.Assert.assertEquals(0,tempAffiliate.getBalance(),0.001);
    }

    public boolean adClickGuard() {
        return getState() == AffiliateOperatorStates.BRONZE || getState() == AffiliateOperatorStates.SILVER || getState() == AffiliateOperatorStates.GOLD;
    }
    public @Action void adClick()
    {
        systemUnderTest.adClicked(1);

        TotalBalance +=0.5;
        CurrentBalance +=0.5;

        //updating model
        if(TotalBalance <50.0)
        { // is in bronze but cannot go to silver yet

            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("SUT does not match model...Should still be bronze when less " +
                    "than 50",bronze, tempAffiliate.getType() == AffiliateType.BRONZE);
        }
        else if(TotalBalance ==50.0)
        {//can be promoted to silver
            modelAffiliate = AffiliateOperatorStates.SILVER;
            bronze = false;
            silver = true;

            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("SUT does not match model...should be SILVER after reaching " +
                    "50", silver, tempAffiliate.getType() == AffiliateType.SILVER);
        }
        else
        if(TotalBalance <500.0)
        {//is in silver but cannot go to gold yet
            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("SUT does not match model...Should still be SILVER when between " +
                    "50 and 500", silver, tempAffiliate.getType() == AffiliateType.SILVER);
        }
        else
        if(TotalBalance ==500.0)
        {//can be promoted to gold
            modelAffiliate = AffiliateOperatorStates.GOLD;
            silver = false;
            gold = true;

            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("SUT does not match model...Should be GOLD after reaching " +
                    "500", gold, tempAffiliate.getType() == AffiliateType.GOLD);
        }
        else
        {   //is in gold therefore cannot go further
            //Checking correspondence between model and SUT.
            org.junit.Assert.assertEquals("SUT does not match model...Should be GOLD when balance is " +
                    "greater than 500", gold, tempAffiliate.getType() == AffiliateType.GOLD);
        }

        //Checking correspondence between model and SUT.
        org.junit.Assert.assertEquals(TotalBalance,tempAffiliate.getCumulativeTotal(),0.001);
    }


    @Test
    public void RandomTesting() {

       final RandomTester tester = new RandomTester(new AffiliateOperatorModel());
        tester.setResetProbability(0.001); // long test sequences

        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        tester.buildGraph(); //Builds a model of our FSM to ensure that the coverage metrics are correct.

        tester.addListener(new StopOnFailureListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.

        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have ben executed during the execution of the test.

        tester.generate(2000); //Generates 500 transitions
        tester.printCoverage(); //Prints the coverage metrics specified above.
    }


    @Test
    public void GreedyTesting() {
        //Creates a test generator that can generate random walks.
        // A greedy random walk gives preference to transitions that have never been taken before.
        // Once all transitions out of a state have been taken, it behaves the same as a random walk.

        final RandomTester tester = new GreedyTester(new AffiliateOperatorModel());

        tester.setResetProbability(0.001); // long test sequences

        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        tester.buildGraph(); //Builds a model of our FSM to ensure that the coverage metrics are correct.

        tester.addListener(new StopOnFailureListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.

        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have ben executed during the execution of the test.

        tester.generate(2000); //Generates 500 transitions
        tester.printCoverage(); //Prints the coverage metrics specified above.
    }

    @Test
    public void LookAheadTesting() {

        final LookaheadTester tester = new LookaheadTester(new AffiliateOperatorModel());

        tester.setDepth(10);
        tester.setNewActionValue(50);
        tester.setNewTransValue(100);

//        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        //tester.buildGraph(); //Builds a model of our FSM to ensure that the coverage metrics are correct.

        tester.addListener(new VerboseListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener(new StopOnFailureListener());
        //tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.

        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have ben executed during the execution of the test.

        tester.generate(7); //Generates 500 transitions

        tester.printCoverage(); //Prints the coverage metrics specified above.
    }

    @Test
    public void AllRoundTesting() {
//
        final AllRoundTester tester = new AllRoundTester(new AffiliateOperatorModel());
        tester.setLoopTolerance(2000);


        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        tester.buildGraph(); //Builds a model of our FSM to ensure that the coverage metrics are correct.

//        tester.addListener(new VerboseListener()); //original Place
        tester.addListener(new StopOnFailureListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.

        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have ben executed during the execution of the test.

        tester.generate(8000); //Generates 500 transitions
        tester.printCoverage(); //Prints the coverage metrics specified above.
    }
}
