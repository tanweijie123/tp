package seedu.address.testutil;

import seedu.address.logic.commands.session.EditSessionCommand.EditSessionDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.session.SessionParserUtil;
import seedu.address.model.session.Gym;
import seedu.address.model.session.ExerciseType;
import seedu.address.model.session.Interval;
import seedu.address.model.session.Session;

import java.time.LocalDateTime;

/**
 * A utility class to help with building EditSessionDescriptor objects.
 */
public class EditSessionDescriptorBuilder {

    private EditSessionDescriptor descriptor;

    public EditSessionDescriptorBuilder() {
        descriptor = new EditSessionDescriptor();
    }

    public EditSessionDescriptorBuilder(EditSessionDescriptor descriptor) {
        this.descriptor = new EditSessionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditSessionDescriptor} with fields containing {@code Session}'s details
     */
    public EditSessionDescriptorBuilder(Session Session) {
        descriptor = new EditSessionDescriptor();
        descriptor.setGym(Session.getGym());
        descriptor.setExerciseType(Session.getExerciseType());
        descriptor.setInterval(Session.getInterval());
    }

    /**
     * Sets the {@code Gym} of the {@code EditSessionDescriptor} that we are building.
     */
    public EditSessionDescriptorBuilder withGym(String gym) {
        descriptor.setGym(new Gym(gym));
        return this;
    }

    /**
     * Sets the {@code ExerciseType} of the {@code EditSessionDescriptor} that we are building.
     */
    public EditSessionDescriptorBuilder withExerciseType(String exerciseType) {
        descriptor.setExerciseType(new ExerciseType(exerciseType));
        return this;
    }

    /**
     * Sets the {@code Interval} of the {@code EditSessionDescriptor} that we are building.
     */
    public EditSessionDescriptorBuilder withInterval(String start_time, int duration) throws ParseException {
        descriptor.setInterval(new Interval(SessionParserUtil.parseStringToDateTime(start_time), duration));
        return this;
    }

    public EditSessionDescriptor build() {
        return descriptor;
    }
}
