package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.session.ExerciseType;
import seedu.address.model.session.Gym;
import seedu.address.model.session.Interval;
import seedu.address.model.session.Session;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Client objects.
 */
public class ScheduleBuilder {

    public static final String DEFAULT_CLIENT_NAME = "Alice Pauline";
    public static final String DEFAULT_CLIENT_PHONE = "94351253";
    public static final String DEFAULT_CLIENT_EMAIL = "alice@example.com";
    public static final String DEFAULT_CLIENT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_CLIENT_TAGS = "injured-thigh";

    public static final int DEFAULT_DURATION = 120;
    public static final String DEFAULT_EXERCISE_TYPE = "Endurance";
    public static final String DEFAULT_GYM = "Getwell gym";
    public static final LocalDateTime DEFAULT_START_TIME =
            LocalDateTime.of(2020, 9, 29, 13, 0);

    private Name clientName;
    private Phone clientPhone;
    private Email clientEmail;
    private Address clientAddress;
    private Set<Tag> clientTags;

    private Gym gym;
    private ExerciseType exerciseType;
    private Interval interval;

    /**
     * Creates a {@code ScheduleBuilder} with the default details.
     */
    public ScheduleBuilder() {
        clientName = new Name(DEFAULT_CLIENT_NAME);
        clientPhone = new Phone(DEFAULT_CLIENT_PHONE);
        clientEmail = new Email(DEFAULT_CLIENT_EMAIL);
        clientAddress = new Address(DEFAULT_CLIENT_ADDRESS);
        clientTags = new HashSet<Tag>();
        clientTags.add(new Tag(DEFAULT_CLIENT_TAGS));

        gym = new Gym(DEFAULT_GYM);
        exerciseType = new ExerciseType(DEFAULT_EXERCISE_TYPE);
        interval = new Interval(DEFAULT_START_TIME, DEFAULT_DURATION);
    }

    /**
     * Initializes the ScheduleBuilder with the data of {@code ClientToCopy}.
     */
    public ScheduleBuilder(Schedule scheduleToCopy) {
        clientName = scheduleToCopy.getClient().getName();
        clientPhone = scheduleToCopy.getClient().getPhone();
        clientEmail = scheduleToCopy.getClient().getEmail();
        clientAddress = scheduleToCopy.getClient().getAddress();
        clientTags = scheduleToCopy.getClient().getTags();

        gym = scheduleToCopy.getSession().getGym();
        exerciseType = scheduleToCopy.getSession().getExerciseType();
        interval = scheduleToCopy.getSession().getInterval();
    }

    /**
     * Sets the {@code Name} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withName(String name) {
        this.clientName = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withTags(String ... tags) {
        this.clientTags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withAddress(String address) {
        this.clientAddress = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withPhone(String phone) {
        this.clientPhone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withEmail(String email) {
        this.clientEmail = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Gym} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withGym(String gym) {
        this.gym = new Gym(gym);
        return this;
    }

    /**
     * Sets the {@code ExerciseType} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withExerciseType(String exerciseType) {
        this.exerciseType = new ExerciseType(exerciseType);
        return this;
    }

    /**
     * Sets the {@code Interval} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withInterval(LocalDateTime start, int duration) {
        this.interval = new Interval(start, duration);
        return this;
    }

    /**
     * Returns the {@code Schedule} based on this properties.
     */
    public Schedule build() {
        Client client = new Client(clientName, clientPhone, clientEmail, clientAddress, clientTags);
        Session session = new Session(gym, exerciseType, interval);
        return new Schedule(client, session);
    }

}
