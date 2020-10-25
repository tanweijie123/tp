package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.util.WeightUnit;

public class WeightTest {

    @Test
    public void constructor_invalidWeight_throwsIllegalArgumentException() {
        double invalidWeight = -3.6;
        assertThrows(IllegalArgumentException.class, () -> new Weight(invalidWeight));
    }

    @Test
    public void constructor_invalidUnit_throwsIllegalArgumentException() {
        double validWeight = 63.1;
        String unit = "g";
        assertThrows(IllegalArgumentException.class, () -> new Weight(validWeight, new WeightUnit(unit)));
    }

    @Test
    public void constructor_validUnit_throwsIllegalArgumentException() {
        double validWeight = 63.1;
        String unit = "kg";
        assertTrue(Weight.isValidWeight(new Weight(validWeight, new WeightUnit(unit)).getWeight()));
    }

    @Test
    public void isValidWeight() {
        // eq: negative weight
        assertThrows(NullPointerException.class, () -> Weight.isValidWeight(null));

        // eq: 0 weight
        assertFalse(Weight.isValidWeight(0));
        assertFalse(Weight.isValidWeight(Weight.getDefaultWeight()));

        // eq: valid weight
        assertTrue(Weight.isValidWeight(79.1));
        assertTrue(Weight.isValidWeight(new Weight(100.2)));
        assertTrue(Weight.isValidWeight(Double.MAX_VALUE)); // max double

        // eq: valid weight with unit
        assertTrue(Weight.isValidWeight(new Weight(100.2)));
    }

    @Test
    public void equalsTest() {
        Weight firstWeight = new Weight(36.8);
        Weight secondWeight = new Weight(67.2);
        Weight similarToSecondWeight = new Weight(67.2);


        assertTrue(firstWeight.equals(firstWeight));
        assertTrue(secondWeight.equals(secondWeight));
        assertTrue(similarToSecondWeight.equals(similarToSecondWeight));

        assertFalse(firstWeight.equals(secondWeight));
        assertTrue(secondWeight.equals(similarToSecondWeight));
    }

    @Test
    public void hashCodeTest() {
        Double weightInDouble = Double.valueOf("82.1");
        Weight weight = new Weight(weightInDouble);

        assertTrue(weight.hashCode() == weightInDouble.hashCode());
    }
}
