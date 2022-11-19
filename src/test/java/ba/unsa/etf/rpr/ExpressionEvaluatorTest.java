package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionEvaluatorTest {

    @Test
    void evaluateTest1() {
        ExpressionEvaluator ee = new ExpressionEvaluator();
        Double rez = ee.evaluate( "( 100 + ( 3 * 3 ) )" );

        assertEquals( 109.0, rez );
    }

    @Test
    void evaluateTest2() {
        ExpressionEvaluator ee = new ExpressionEvaluator();
        Double rez = ee.evaluate( "( 2 - ( 3 * 3 ) )" );

        assertNotEquals( 7, rez );
    }

    @Test
    void evaluateTest3() {
        ExpressionEvaluator ee = new ExpressionEvaluator();
        Double rez = ee.evaluate( "( 10 + ( ( 2 * 3 ) / ( 3 - 7 ) ) )" );

        assertAll( () -> assertEquals( 8.5, rez ), () -> assertNotEquals(-8.5, rez) );
    }

    @Test
    void evaluateTest4() {
        ExpressionEvaluator ee = new ExpressionEvaluator();
        Double rez = ee.evaluate("( 3 - ( 4 / 2 ) )");

        assertTrue( rez.equals(1.0) );
    }

    @Test
    void evaluateTest5() {
        ExpressionEvaluator ee = new ExpressionEvaluator();
        Double rez = ee.evaluate("( 0 + ( 0 - 0 ) )");

        assertFalse( rez > 0 );
    }

    @Test
    void evaluateTestGreska1() {
        ExpressionEvaluator ee = new ExpressionEvaluator();

        assertThrows(RuntimeException.class,() -> {
            ee.evaluate("( 1 + 2 (");
        });
    }

    @Test
    void evaluateTestGreska2() {
        ExpressionEvaluator ee = new ExpressionEvaluator();

        assertThrows(RuntimeException.class,() -> {
            ee.evaluate("( 1 + ( 2 + 3 )");
        });
    }

    @Test
    void evaluateTestGreska3() {
        ExpressionEvaluator ee = new ExpressionEvaluator();

        assertDoesNotThrow( () -> {
            Double rez = ee.evaluate("( 1 + 2 )");
        } );
    }

}