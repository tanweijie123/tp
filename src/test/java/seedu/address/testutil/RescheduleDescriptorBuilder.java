package seedu.address.testutil;

import seedu.address.commons.core.index.Index;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.ExerciseType;
import seedu.address.model.session.Gym;

import seedu.address.logic.commands.schedule.RescheduleCommand.RescheduleDescriptor;

/**
 * A utility class to help with building RescheduleDescriptorBuilder objects.
 */
public class RescheduleDescriptorBuilder {

    private RescheduleDescriptor descriptor;

    public RescheduleDescriptorBuilder() {
        this.descriptor = new RescheduleDescriptor();
    }

    public RescheduleDescriptorBuilder(RescheduleDescriptor descriptor) {
        this.descriptor = new RescheduleDescriptor(descriptor);
    }

//    /**
//     * Returns an {@code RescheduleDescriptorBuilder} with fields containing {@code Schedule}'s details
//     */
//    public RescheduleDescriptorBuilder(Schedule schedule) {
//        descriptor = new RescheduleDescriptor();
//        descriptor.setClientIndex(schedule.getClient());
//        descriptor.setSessionIndex(schedule.getSession());
//    }

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
