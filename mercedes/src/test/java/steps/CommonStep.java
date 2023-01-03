package steps;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import pages.CommonPage;
import report.ScenarioTestResult;
import utils.helpers.Action;
import utils.helpers.Helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommonStep {

    CommonPage page;

    public static String ssPath;

    public static int currentStepDefIndex;

    public static List<ScenarioTestResult.Step> steps;

    public void getSS() {
        ssPath = Helper.getSS().getAbsolutePath();
    }

    private void getSteps(Scenario scenario) throws Exception {
        String testScenario = scenario.getName().toString();
        Scenario scenarioObj = scenario;
        Field f1 = scenario.getClass().getDeclaredField("delegate");
        f1.setAccessible(true);
        TestCaseState r = (TestCaseState) f1.get(scenario);

        Field f2 = r.getClass().getDeclaredField("testCase");
        f2.setAccessible(true);
        TestCase r2 = (TestCase) f2.get(r);

        List<PickleStepTestStep> stepDefs = r2.getTestSteps()
                .stream()
                .filter(x -> x instanceof PickleStepTestStep)
                .map(x -> (PickleStepTestStep) x)
                .collect(Collectors.toList());

        steps = new ArrayList<>();
        for (PickleStepTestStep step : stepDefs) {
            steps.add(new ScenarioTestResult.Step(steps.size() + 1, step.getStep().getText()));
        }
    }

    @Before
    public void setUp(Scenario scenario) throws Exception {
        page = new CommonPage();
        currentStepDefIndex = 0;

        getSteps(scenario);
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("beforeStep");
    }

    @AfterStep
    public void afterStep() {
        System.out.println("afterStep");
        currentStepDefIndex += 1;
    }

    @After
    public void tearDown() {
        getSS();
        page.tearDown();
    }

    @Then("Click {string} element")
    public void click(String key) {
        page.click(key);
    }

    @And("Fill {string} field with {string}")
    public void fillByToWith(String key, String text) {
        page.fill(key, text);
    }

    @Then("Check equality of {string} field with {string}")
    public void checkByToWith(String key, String text) {
        page.checkEquals(key, text);
    }

    @When("Check {string} text existence on page")
    public void checkElementShownOnPageWith(String key) {
        page.checkWithText(key);
    }

    @When("Check {string} field existence on page")
    public void checkPlaceholderOnPageWith(String key) {
        page.checkWithPlaceholderText(key);
    }

    @Then("Clear {string} input field")
    public void clearText(String key) {
        page.clearText(key);
    }

    @Given("^Page element control$")
    public void pageElementControl(DataTable dataTable) {
        List<List<String>> elementList = dataTable.asLists(String.class);
        for (List<String> e : elementList) {
            page.elementControl(e);
        }
    }

    @Given("^Page texts control$")
    public void pageTextControl(List<String> textList) {
        for (String t : textList) {
            page.checkWithText(t);
        }
    }

    @Then("Refresh page")
    public void refreshPage() {
        page.refreshPage();
    }

    @And("Wait for given seconds {int}")
    public void waitForGivenSeconds(int seconds) {
        page.waitFor(seconds);
    }

    @Then("Wait for the {string} element to display on screen")
    public void waitForElementToDisplay(String key) {
        page.waitForTheElementDisplay(key);
    }

    @Then("Wait for the {string} element to be clickable")
    public void waitForElementToClickable(String key) {
        page.waitForTheElementClickable(key);
    }

    @When("Check page url contains {string}")
    public void checkPageUrlContains(String url) {
        page.verifyUrl(url);
    }

    @Then("Check equality of {string} field inner text with {string}")
    public void checkTextWithGetText(String key, String text) {
        page.checkInputEquality(key, text);
    }

    @When("Check page title contains {string}")
    public void checkPageTitleContains(String title) {
        page.verifyPageTitle(title);
    }

    @And("Hover on {string} element")
    public void hoverOnElementByAnd(String key) {
        page.hoverOnElement(key);
    }

    @And("Wait until {string} element is visible")
    public void waitUntilVisible(String key) {
        page.waitUntilElementVisible(key);
    }

    @Then("Check {string} input field contains {string} text")
    public void checkFilledInputContains(String key, String text) {
        page.checkFilledInputContains(key, text);
    }

    @When("Get {string} element attribute with {string} value")
    public void getAttValue(String key, String value) {
        page.getAttributeValue(key, value);
    }

    @Then("Get {string} element attribute with {string} value and check equality with {string}")
    public void getAttributeValueAndVerifyWithGiven(String key, String getValue, String value) {
        page.getAttributeValueAndVerify(key, getValue, value);
    }

    @And("Click Escape Key")
    public void clickEscapeKey() {
        page.clickEscapeKey();
    }

    @When("Fill {string} field value with {string}")
    public void fillFieldValueWith(String key, String text) {
        page.clearValueAndFill(key, text);
    }

    @When("Clear and fill {string} field value with {string}")
    public void clearAndFillFieldValueWith(String key, String text) {
        page.fill(key, text);
    }

    @And("Click Enter")
    public void clickEnter() {
        page.clickEnter();
    }

}