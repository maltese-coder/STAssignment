
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        format = {"html:target/cucumber-html-report"},
        features = {"src/test/resources/features"}
       // glue = "Cucumber"
)

public class RunCucumberTests {
}
