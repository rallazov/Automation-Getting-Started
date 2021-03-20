package org.automation.junit5.tests;

import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import org.automation.junit5.core.CalculatorTestBase;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Month;

public class ParameterizedTestExample extends CalculatorTestBase {

    @ParameterizedTest
    @CsvFileSource(resources = "/addition.csv",numLinesToSkip = 1)
    public void testCSVfileParameter(String a, String b, String result){
        assertEquals(Double.valueOf(result).doubleValue(),myCal.add(Double.valueOf(a).doubleValue(),Double.valueOf(b).doubleValue()));
    }

    @ParameterizedTest
    @CsvSource({})
    public void testCSVParseParameter(){

    }

    @ParameterizedTest
    @ValueSource(ints = {5,6,7})
    public void testValueSource(int input){
        assertEquals(input*2,myCal.add(input,input));
    }

    @ParameterizedTest
    @NullSource
    public void testNullSource(String input_null){
        assertTrue(null==input_null);
    }
    @ParameterizedTest
    @EmptySource
    public void testEmptySource(String input_empty){
        assertTrue(input_empty.equals(""));
    }

    /***
     * One of the test will fail as it gives array of one null & empty string
     * @param input_Null_empty
     */
    @ParameterizedTest
    @NullAndEmptySource
    public void testEmptyNullSource(String input_Null_empty){
        assertTrue(input_Null_empty.equals(""));
    }
    @ParameterizedTest
    @EnumSource(Month.class)
    public void testEnumSource(Month month){
        assertTrue(month.getValue()>=1&& month.getValue()<=12);
    }
    @ParameterizedTest
    @EnumSource
    public void testEnumSourceNoClass(Month month){
        assertTrue(month.getValue()>=1&& month.getValue()<=12);
    }

}
