package test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.List;

public class AddEmployeeTest extends CommonMethods {
    //read the configuration file for username and password
    //and add an employee

    @Test
    public void addEmployee() {
       // login.usernameBox.sendKeys(ConfigReader.getPropertyValue("username"));
      //  login.passwordBox.sendKeys(ConfigReader.getPropertyValue("password"));
       // login.loginBtn.click();
        login.LoginMethod(ConfigReader.getPropertyValue("username"),ConfigReader.getPropertyValue("password"));
        click(dash.pimOption);
        click(dash.addEmployeeButton);

        sendText(addEmployeePage.firstNameField, "andrew");
        sendText(addEmployeePage.middleNameField, "libery");
        sendText(addEmployeePage.lastNameField, "bobbity");
        //click(addEmployeePage.saveButton);   //moving it below so to grab empID prior to saving

        //get the employee id
        String empID=addEmployeePage.empIDLocator.getAttribute("value");
        click(addEmployeePage.saveButton);
        System.out.println(empID);
        click(dash.pimOption);//error starts here fix it
        click(dash.employeeListOption);

        sendText(employeeSearchPage.idField,empID);
        click(employeeSearchPage.searchButton);

        //List<WebElement> rowData= driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
        List<WebElement> rowData= employeeSearchPage.rowData;
            for (WebElement data:rowData){
                System.out.println(data.getText());
                String ActualID=data.getText();
                Assert.assertEquals(empID,ActualID);
        }
    }
    @Test
    public void addMultipleEmployee(){
        //read employee data from excel file
        //assert that you have successfully added employee
    }
}
