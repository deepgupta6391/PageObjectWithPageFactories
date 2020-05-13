package com.w2a.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.w2a.base.Page;
import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;
import com.w2a.utilities.Utilities;

public class CustomListeners extends Page implements ITestListener, ISuiteListener {

	String messageBody;
	
	public void onTestStart(ITestResult result) {

		test = rep.createTest(result.getName() + "     @TestCase : " + result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {
		//test.log(Status.PASS, result.getName().toUpperCase() + " PASS");
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName().toUpperCase()+"-Test case PASSED", ExtentColor.GREEN));
		rep.flush();
	}

	public void onTestFailure(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Extent Report
		//test.log(Status.FAIL, result.getName().toUpperCase() + " Failed with exception : " + result.getThrowable());
		
		String exceptionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable()+" -- TEST CASE FAILED", ExtentColor.RED));
		test.log(Status.INFO, ("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" +exceptionMessage.replaceAll(",", "<br>")+"</details>"+" \n"));
	
		
		try {
			test.info("Failed test screenshot :"+"&nbsp;&nbsp;", MediaEntityBuilder.createScreenCaptureFromPath(
					System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + Utilities.screenshotName)
					.build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//testNG Report
		Reporter.log("Click to see screenshot");
		Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + "><img src=" + Utilities.screenshotName
				+ " height=200 width=200></img></a>");
		rep.flush();
	}

	public void onTestSkipped(ITestResult result) {
		//test.log(Status.SKIP, result.getName().toUpperCase() + " Skipped the test as the runmode is NO");
		String exceptionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName().toUpperCase()+"-Test case SKIPPED", ExtentColor.ORANGE));
		//test.log(Status.SKIP, MarkupHelper.createLabel(result.getThrowable()+"-Test case SKIPPED", ExtentColor.ORANGE));
		test.log(Status.INFO, ("<details>" + "<summary>" + "<b>" + "<font color=" + "orange>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" +exceptionMessage.replaceAll(",", "<br>")+"</details>"+" \n"));
		rep.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Failure of test cases and its details are : " + result.getName());
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ISuite result) {	
		MonitoringMail mail = new MonitoringMail();
		
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/LiveProject-PageObjectWithPageFactories/Extent_20Report/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onStart(ISuite result) {

	}

}
