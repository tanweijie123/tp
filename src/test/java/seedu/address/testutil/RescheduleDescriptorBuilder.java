package seedu.address.testutil;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedule.RescheduleCommand.RescheduleDescriptor;

/**
 * A utility class to help with building RescheduleDescriptorBuilder objects.
 */
public class RescheduleDescriptorBuilder {

    private RescheduleDescriptor descriptor;

    public RescheduleDescriptorBuilder(RescheduleDescriptor descriptor) {
        this.descriptor = new RescheduleDescriptor(descriptor);
    }

    /**
     * Returns an {@code RescheduleDescriptorBuilder} with fields containing {@code Schedule}'s details
     */
    public RescheduleDescriptorBuilder() {
        descriptor = new RescheduleDescriptor();
        descriptor.setClientIndex(INDEX_FIRST_CLIENT);
        descriptor.setSessionIndex(INDEX_FIRST_SESSION);
    }

    /**
     * Sets the {@code Index} of the {@code RescheduleDescriptorBuilder} that we are building.
     */
    public RescheduleDescriptorBuilder withClientIndex(Index clientIndex) {
        descriptor.setClientIndex(clientIndex);
        return this;
    }

    /**
     * Sets the {@code Index} of the {@code RescheduleDescriptorBuilder} that we are building.
     */
    public RescheduleDescriptorBuilder withSessionIndex(Index sessionIndex) {
        descriptor.setSessionIndex(sessionIndex);
        return this;
    }


    public RescheduleDescriptor build() {
        return descriptor;
    }
}
