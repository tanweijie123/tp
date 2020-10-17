package seedu.address.testutil;

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
