package seedu.address.logic.commands.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.client.ClientCommandTestUtil.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedule.RescheduleCommand.RescheduleDescriptor;
import seedu.address.testutil.RescheduleDescriptorBuilder;

public class RescheduleDescriptorTest {

    //    @Test
    //    public void equals() {
    //        // same values -> returns true
    //        RescheduleDescriptor descriptorWithSameValues = new RescheduleDescriptor(DESC_AMY);
    //        assertTrue(DESC_AMY.equals(descriptorWithSameValues));
    //
    //        // same object -> returns true
    //        assertTrue(DESC_AMY.equals(DESC_AMY));
    //
    //        // null -> returns false
    //        assertFalse(DESC_AMY.equals(null));
    //
    //        // different types -> returns false
    //        assertFalse(DESC_AMY.equals(5));
    //
    //        // different values -> returns false
    //        assertFalse(DESC_AMY.equals(DESC_BOB));
    //
    //        // different client index -> returns false
    //        RescheduleDescriptor editedAmy = new RescheduleDescriptorBuilder(DESC_AMY).withClientIndex(VALID_NAME_BOB).build();
    //        assertFalse(DESC_AMY.equals(editedAmy));
    //
    //        // different session index -> returns false
    //        editedAmy = new RescheduleDescriptorBuilder(DESC_AMY).withSessionIndex(VALID_PHONE_BOB).build();
    //        assertFalse(DESC_AMY.equals(editedAmy));
    //
    //    }
}
