package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WeightUnitTest {

    @Test
    public void constructor_invalidWeightUnit_throwsIllegalArgumentException() {
        String invalidWeightUnit = "g";
        assertThrows(IllegalArgumentException.class, () -> new WeightUnit(invalidWeightUnit));
    }

    @Test
    public void isValidWeightUnit() {
        // eq: null
        assertThrows(NullPointerException.class, () -> WeightUnit.isValidUnit(null));

        // eq: valid weight
        assertTrue(WeightUnit.isValidUnit("kg"));
        assertTrue(WeightUnit.isValidUnit("lb"));
    }

    @Test
    public void equalsTest() {
        WeightUnit firstWeightUnit = new WeightUnit("kg");
        WeightUnit secondWeightUnit = new WeightUnit("lb");
        WeightUnit similarToSecondWeightUnit = new WeightUnit("lb");

        assertEquals(firstWeightUnit, firstWeightUnit);
        assertEquals(secondWeightUnit, secondWeightUnit);
        assertEquals(similarToSecondWeightUnit, similarToSecondWeightUnit);

        assertNotEquals(secondWeightUnit, firstWeightUnit);
        assertEquals(similarToSecondWeightUnit, secondWeightUnit);
    }

    @Test
    public void hashCodeTest() {
        String weightUnitInString = "kg";
        WeightUnit weightUnit = new WeightUnit(weightUnitInString);

        assertEquals(weightUnitInString.hashCode(), weightUnit.hashCode());
    }
}
