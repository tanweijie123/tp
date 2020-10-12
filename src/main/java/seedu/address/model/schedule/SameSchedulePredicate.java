package seedu.address.model.schedule;

import java.util.function.Predicate;

public class SameSchedulePredicate implements Predicate<Schedule> {
    private final Schedule schedule;

    public SameSchedulePredicate(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean test(Schedule otherSchedule) {
        return schedule.isExisting(otherSchedule);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SameSchedulePredicate // instanceof handles nulls
                && schedule.equals(((SameSchedulePredicate) other).schedule)); // state check
    }
}
