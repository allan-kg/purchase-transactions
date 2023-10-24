package kg.allan.purchasetransactions.feature;

import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 *
 * @author allan
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("kg/allan/purchasetransactions")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "kg.allan.purchasetransactions")
//@ConfigurationParameter(key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
//@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@RunMe")
public class RunCucumberTests {
    
}