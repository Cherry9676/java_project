package stepdefinitions;
    import io.cucumber.java.en.*;
    import workflows.SeleniumWorkFlow;
    import org.junit.Assert;
    import common.Assertion;
    import common.WebBrowserUtil;
    public class NewFeatureStepDefinition
	{
        SeleniumWorkFlow workFlow = new SeleniumWorkFlow();
        

            @When("^I checked Checkbox1 in the internet$")			
            public void WhenICheckedCheckbox1InTheInternet()
            {
                workFlow.checkCheckbox(0,"New Feature","New Feature.Checkbox1CheckBoxXPATH","XPATH");
                
            }

             @Then("^verify checked Checkbox1 in the internet$")			
            public void ThenVerifyCheckedCheckbox1InTheInternet()
            {
                Assertion.IsTrue(workFlow.verifyChecked(0,"New Feature","New Feature.Checkbox1CheckBoxXPATH","XPATH"), "Then verify checked Checkbox1 in the internet");
                WebBrowserUtil.captureScreenshot();
                
            }

            @When("^I unchecked Checkbox1 in the internet$")			
            public void WhenIUncheckedCheckbox1InTheInternet()
            {
                workFlow.uncheckCheckBox(0,"New Feature","New Feature.Checkbox1CheckBoxXPATH","XPATH");
                
            }

             @Then("^verify unchecked Checkbox1 in the internet$")			
            public void ThenVerifyUncheckedCheckbox1InTheInternet()
            {
                Assertion.IsTrue(workFlow.verifyUnchecked(0,"New Feature","New Feature.Checkbox1CheckBoxXPATH","XPATH"), "Then verify unchecked Checkbox1 in the internet");
                WebBrowserUtil.captureScreenshot();
                
            }
    }