package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.session.SessionParserUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.schedule.PaymentStatus;
import seedu.address.model.schedule.Remark;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.Weight;
import seedu.address.model.session.ExerciseType;
import seedu.address.model.session.Gym;
import seedu.address.model.session.Interval;
import seedu.address.model.session.Session;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Client[] getSampleClients() {
        return new Client[]{
            new Client(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("injured-thigh")),
            new Client(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("allergy-nuts", "injured-thigh")),
            new Client(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("injured-back")),
            new Client(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("allergy-dairy")),
            new Client(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("injured-thumb")),
            new Client(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("allergy-nuts"))
        };
    }

    public static Session[] getSampleSession() {
        try {
            return new Session[]{
                new Session(new Gym("Getwell gym"), new ExerciseType("Endurance"),
                    new Interval(SessionParserUtil.parseStringToDateTime("29/09/2020 1300"), 120)),
                new Session(new Gym("Machoman gym"), new ExerciseType("Bodybuilder"),
                    new Interval(SessionParserUtil.parseStringToDateTime("29/09/2020 1600"), 150)),
                new Session(new Gym("Machoman gym"), new ExerciseType("Bodybuilder"),
                    new Interval(SessionParserUtil.parseStringToDateTime("01/10/2020 1800"), 120))
            };
        } catch (ParseException e) {
            //INFO: if you reach here, it means the date input above is wrong.
            System.err.println("Default data not initialised. ");
            throw new UnsupportedOperationException(); //cannot return null
        }
    }

    public static Schedule[] getSampleSchedule() {
        try {
            return new Schedule[]{
                new Schedule(
                    new Client(new Name("Bernice Yu"), new Phone("99272758"),
                        new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("allergy-nuts", "injured-thigh")),
                    new Session(new Gym("Getwell gym"), new ExerciseType("Endurance"),
                        new Interval(SessionParserUtil.parseStringToDateTime("29/09/2020 1300"), 120)),
                        PaymentStatus.PAYMENT_STATUS_UNPAID, Remark.EMPTY_REMARK, Weight.getDefaultWeight()),
                new Schedule(
                    new Client(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("injured-thigh")),
                    new Session(new Gym("Machoman gym"), new ExerciseType("Bodybuilder"),
                        new Interval(SessionParserUtil.parseStringToDateTime("29/09/2020 1600"), 150)),
                        new PaymentStatus(PaymentStatus.VALUE_PAID),
                        new Remark("client request to increase reps for next session"), new Weight(77.3)),
                new Schedule(
                        new Client(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                                new Address("Blk 30 Geylang Street 29, #06-40"),
                                getTagSet("injured-thigh")),
                        new Session(new Gym("Machoman gym"), new ExerciseType("Bodybuilder"),
                                new Interval(SessionParserUtil.parseStringToDateTime("01/10/2020 1800"), 120)),
                        new PaymentStatus(PaymentStatus.VALUE_PAID),
                        new Remark("client struggling; reduce rep next session"), new Weight(77.9))
            };
        } catch (ParseException e) {
            //INFO: if you reach here, it means the date input above is wrong.
            System.err.println("Default data not initialised. ");
            throw new UnsupportedOperationException(); //cannot return null
        }
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }
        for (Session sampleSession : getSampleSession()) {
            sampleAb.addSession(sampleSession);
        }
        for (Schedule sampleSchedule : getSampleSchedule()) {
            sampleAb.addSchedule(sampleSchedule);
        }

        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
