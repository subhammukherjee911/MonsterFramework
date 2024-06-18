package Runner;

import Utilities.Configuration;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "stepDefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = ""
)
public class TestRunner
{
    public static void main(String[] args) throws IOException
    {
        Configuration configuration = new Configuration();
        String tag = configuration.getTags();
        String[] tags = {tag};
        io.cucumber.core.cli.Main.run(tags);
    }
}
