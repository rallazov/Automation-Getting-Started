package org.automation.junit5.tests.parameterized;

import org.automation.junit5.core.CalculatorTestBase;

import static org.automation.junit5.core.StringUtil.isBlank;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Month;
import java.util.stream.Stream;

public class TestParameterized extends CalculatorTestBase {

    static Stream<Arguments> arguments = Stream.of(
            Arguments.of(null,true),
            Arguments.of("",true),
            Arguments.of(" ",true),
            Arguments.of("shantonu",false));

    /**
     * Custom Annotation
     * TO DO : Currently Not Working
     * @param input
     * @param expected
     */
    @ParameterizedTest
    @VariableStream("arguments")
    public void customAnnotationForInput(String input, boolean expected){
    assertEquals(expected, isBlank(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addition.csv",numLinesToSkip = 1)
    public void testCSVfileParameter(String a, String b, String result){
        assertEquals(Double.valueOf(result).doubleValue(),myCal.add(Double.valueOf(a).doubleValue(),Double.valueOf(b).doubleValue()));
    }

    @ParameterizedTest
    @CsvSource({"shantonu,SHANTONU","teSt, TEST","Java,JAVA"})
    public void testCSV(String input, String expected){
    assertEquals(expected,input.toUpperCase());
    }
    @ParameterizedTest
    @CsvSource(value = {"shantonu|SHANTONU","teSt|TEST","Java|JAVA",},delimiter = '|')
    public void testCSVWithDelimiter(String input, String expected){
        assertEquals(expected,input.toUpperCase());
    }

    @ParameterizedTest
    @MethodSource("org.automation.junit5.core.StringParameters#emptyStrings")
    public void testMethodSource(String input){
        assertTrue(isBlank(input));
    }

    /***
     * Custom Argument Provider
     * @param input
     */
    @ParameterizedTest
    @ArgumentsSource(ErrorStringProvider.class)
    public void testArgumentsSource(String input){
        assertTrue(isBlank(input));
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
        assertTrue(isBlank(input_Null_empty));
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

    /**
     * Non Leap Year testing
     * false = not leap year
     * @param month
     */
    @ParameterizedTest
    @EnumSource(value = Month.class, names = {"JANUARY","MARCH","MAY","JULY"})
    public void testEnumSourceWithData(Month month){
        assertEquals(31,month.length(false));
    }
    @ParameterizedTest
    @EnumSource(value = Month.class, names = { "FEBRUARY","APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"}, mode = EnumSource.Mode.EXCLUDE)
    public void testEnumSourceExcludeData(Month month){
        assertEquals(31,month.length(false));
    }
    @ParameterizedTest
    @EnumSource(value = Month.class, names = ".+Y",mode = EnumSource.Mode.MATCH_ANY)
    public void testEnumWithDataRegex(Month month){
        assertTrue(month.toString().endsWith("Y"),"Not Ends with Y");

    }
}