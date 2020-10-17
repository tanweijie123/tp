package seedu.address.testutil;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static seedu.address.testutil.TypicalSchedules.IS_PAID_FALSE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedule.EditScheduleCommand.EditScheduleDescriptor;

/**
 * A utility class to help with building EditScheduleDescriptorBuilder objects.
 */
public class EditScheduleDescriptorBuilder {

    private EditScheduleDescriptor descriptor;

    public EditScheduleDescriptorBuilder(EditScheduleDescriptor descriptor) {
        this.descriptor = new EditScheduleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditScheduleDescriptorBuilder} with fields containing {@code Schedule}'s details
     */
    public EditScheduleDescriptorBuilder() {
        descriptor = new EditScheduleDescriptor();
        descriptor.setClientIndex(INDEX_FIRST_CLIENT);
        descriptor.setSessionIndex(INDEX_FIRST_SESSION);
        descriptor.setUpdatedSessionIndex(INDEX_FIRST_SESSION);
    }

    /**
     * Sets the {@code Index} of the {@code EditScheduleDescriptorBuilder} that we are building.
     */
    public EditScheduleDescriptorBuilder withClientIndex(Index clientIndex) {
        descriptor.setClientIndex(clientIndex);
        return this;
    }

    /**
     * Sets the {@code session Index} of the {@code EditScheduleDescriptorBuilder} that we are building.
     */
    public EditScheduleDescriptorBuilder withUpdatedSessionIndex(Index sessionIndex) {
        descriptor.setUpdatedSessionIndex(sessionIndex);
        return this;
    }

    /**
     * Sets the {@code isPaid} of the {@code EditScheduleDescriptorBuilder} that we are building.
     */
    public EditScheduleDescriptorBuilder withUpdatedIsPaid(boolean isPaid) {
        descriptor.setUpdatedIsPaid(isPaid);
        return this;
    }

    /**
     * Sets the {@code updated session Index} of the {@code EditScheduleDescriptorBuilder} that we are building.
     */
    public EditScheduleDescriptorBuilder withSessionIndex(Index sessionIndex) {
        descriptor.setSessionIndex(sessionIndex);
        return this;
    }


    public EditScheduleDescriptor build() {
        return descriptor;
    }
}
