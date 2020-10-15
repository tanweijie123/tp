package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.session.SessionParserUtil;
import seedu.address.testutil.SessionBuilder;

public class IntervalContainsDatetimePredicateTest {

    LocalDateTime FIRST;
    LocalDateTime SECOND;

    {
        try {
            FIRST = SessionParserUtil.parseStringToDateTime("20/09/2020 1300");
            SECOND = FIRST.plusMinutes(60);
        } catch (Exception e) {
            throw new AssertionError("This should not be reached!");
        }
    }


    @Test
    public void equals() {
        IntervalContainsDatetimePredicate firstPredicate =
                new IntervalContainsDatetimePredicate(FIRST);
        IntervalContainsDatetimePredicate secondPredicate =
                new IntervalContainsDatetimePredicate(SECOND);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IntervalContainsDatetimePredicate firstPredicateCopy =
                new IntervalContainsDatetimePredicate(FIRST);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Client -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // Contains interval (start)
        IntervalContainsDatetimePredicate predicate =
                new IntervalContainsDatetimePredicate(FIRST);
        assertTrue(predicate.test(
                new SessionBuilder()
                        .withInterval(SessionParserUtil.parseDateTimeToString(FIRST), "60")
                        .build()));

        // Contains interval (end)
        predicate = new IntervalContainsDatetimePredicate(SECOND);
        assertTrue(predicate.test(
                new SessionBuilder()
                        .withInterval(SessionParserUtil.parseDateTimeToString(FIRST), "60")
                        .build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        IntervalContainsDatetimePredicate predicate = new IntervalContainsDatetimePredicate(SECOND);
        assertTrue(predicate.test(
                new SessionBuilder()
                        .withInterval(SessionParserUtil.parseDateTimeToString(FIRST), "60")
                        .build()));
    }
}

