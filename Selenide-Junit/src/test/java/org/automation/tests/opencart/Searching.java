package org.automation.tests.opencart;

import org.automation.core.TestBase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by shantonu on 8/14/17.
 */
public class Searching extends TestBase {
/*
    @Rule
    public ScreenShooter screenShooter =ScreenShooter.failedTests();*/

    @Test
    public void search_AnItem(){
        open("http://demo.opencart.com");
        screenshot("InitialScreenshot");
        $(By.xpath("//div[@id='search']/input")).val("ipod").pressEnter();
        $(By.xpath("//div[@id='search']/span/button")).click();
        Assertions.assertFalse(true);
    }
}

