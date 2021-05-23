package api.cucumber.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "src/test/java/api/cucumber/tests/spotify.feature" },
        publish = true)
public class Runner {}
