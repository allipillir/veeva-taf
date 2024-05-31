package runner;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;
@ExtendWith(SerenityJUnit5Extension.class)
//@Suite
//@IncludeEngines("cucumber")
//@SelectClasspathResource("/features")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value="io.cucumber.core.plugin.SerenityReporterParallel, pretty")
public class TestRunner {

}
