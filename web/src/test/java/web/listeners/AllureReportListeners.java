package web.listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import web.base.BaseTest;

import java.io.ByteArrayInputStream;

public class AllureReportListeners extends TestListenerAdapter {

    @Override
    public void onTestSuccess(ITestResult result){
        String strTestMethodName = result.getMethod().getConstructorOrMethod().getName();
        try {
            Allure.addAttachment(strTestMethodName+" test Passed", strTestMethodName + " method is passed and screenshot is taken below");
            Allure.addAttachment("Passed test screenshot", new ByteArrayInputStream(((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.BYTES)));
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

    @Override
    public void onTestFailure(ITestResult result) {
        String strTestMethodName = result.getMethod().getConstructorOrMethod().getName();
        try {
            Allure.addAttachment(strTestMethodName+" method Failed", strTestMethodName + " method is failed and screenshot is taken below");
            Allure.addAttachment("Screenshot at Failed Step", new ByteArrayInputStream(((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.BYTES)));
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String strTestMethodName = result.getMethod().getConstructorOrMethod().getName();
        try {
            Allure.addAttachment(strTestMethodName+" method skipped", strTestMethodName + " test is skipped and screenshot is taken below");
            Allure.addAttachment("Screenshot at skipped Step", new ByteArrayInputStream(((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.BYTES)));
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}