package org.automation.junit5.tests.orders;

import org.automation.junit5.core.CalculatorTestBase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class TestAlphanumericOrderExample extends CalculatorTestBase {
}
