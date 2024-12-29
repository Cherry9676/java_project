package stepdefinitions;
    import io.cucumber.java.en.*;
    import workflows.SeleniumWorkFlow;
    import org.junit.Assert;
    import common.Assertion;
    import common.WebBrowserUtil;
    public class TheInternetStepDefinition
	{
        SeleniumWorkFlow workFlow = new SeleniumWorkFlow();
        

             @Then("^verify text Add_Remove in the internet$")			
            public void ThenVerifyTextAddremoveInTheInternet()
            {
                Assertion.IsTrue(workFlow.verifyTextInLink(0,"The Internet","The Internet.Add_RemoveButtonXPATH","XPATH"), "Then verify text Add_Remove in the internet");
                WebBrowserUtil.captureScreenshot();
                
            }

             @Then("^verify displayed Add_Remove in the internet$")			
            public void ThenVerifyDisplayedAddremoveInTheInternet()
            {
                Assertion.IsTrue(workFlow.verifyTextInLink(0,"The Internet","The Internet.Add_RemoveButtonXPATH","XPATH"), "Then verify displayed Add_Remove in the internet");
                WebBrowserUtil.captureScreenshot();
                
            }

             @Then("^verify disabled textbox in the internet$")			
            public void ThenVerifyDisabledTextboxInTheInternet()
            {
                Assertion.IsTrue(workFlow.verifyDisabled(0,"The Internet","The Internet.textboxTextBoxXPATH","XPATH"), "Then verify disabled textbox in the internet");
                WebBrowserUtil.captureScreenshot();
                
            }

             @Then("^verify if visible Enable_disable in the internet$")			
            public void ThenVerifyIfVisibleEnabledisableInTheInternet()
            {
                Assertion.IsTrue(workFlow.verifyIfVisible(0,"The Internet","The Internet.Enable_disableButtonXPATH","XPATH"), "Then verify if visible Enable_disable in the internet");
                WebBrowserUtil.captureScreenshot();
                
            }

            @When("^I clicked Enable_disable in the internet$")			
            public void WhenIClickedEnabledisableInTheInternet()
            {
                workFlow.clickedElement(0,"The Internet","The Internet.Enable_disableButtonXPATH","XPATH");
                
            }

            @When("^I wait in seconds wait in the internet as '(.*)'$")			
            public void WhenIWaitInSecondsWaitInTheInternetAswait(String  _wait)
            {
                workFlow.waitInSeconds(_wait,0,"The Internet","The Internet.waitTextBoxID","ID");
                
            }

             @Then("^verify enabled textbox in the internet$")			
            public void ThenVerifyEnabledTextboxInTheInternet()
            {
                Assertion.IsTrue(workFlow.verifyEnabled(0,"The Internet","The Internet.textboxTextBoxXPATH","XPATH"), "Then verify enabled textbox in the internet");
                WebBrowserUtil.captureScreenshot();
                
            }

             @Then("^'(.*)' is displayed with '(.*)'$")			
            public void ThenpageIsDisplayedWithcontent(String  _page, String _content)
            {
                Assertion.IsTrue(workFlow.VerifyDefaultpageIsdisplayed(_page), "Then '<page>' is displayed with '<content>'");
                Assertion.IsTrue(workFlow.VerifymessageIsDisplayed(_content), "");;
                //Assertion.assertAll();
            }

             @Then("^verify url page in the internet as '(.*)'$")			
            public void ThenVerifyUrlPageInTheInternetAspage1(String  _page1)
            {
                Assertion.IsTrue(workFlow.verifyURL(_page1,0,"The Internet","The Internet.pagePageXPATH","XPATH"), "Then verify url page in the internet as 'page1'");
      WebBrowserUtil.captureScreenshot();
                
            }
    }