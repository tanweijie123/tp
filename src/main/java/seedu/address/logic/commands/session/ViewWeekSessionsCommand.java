package seedu.address.logic.commands;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.session.Session;

/**
 * Finds and lists all Sessions in address book that are within the upcoming week.
 */
public class ViewWeekCommand extends Command {

    public static final String COMMAND_WORD = "vweek";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Replaces your right side bar with a overview "
            + "of your sessions in the next 7 days.\n";

    public static final Predicate<Session> PREDICATE_SHOW_UPCOMING_WEEK_SESSIONS = (session) ->
            session.getInterval().getStart()
            .isBefore(LocalDateTime.now().truncatedTo(DAYS).plusDays(7))
            && session.getInterval().getStart().isAfter(LocalDateTime.now().truncatedTo(DAYS));

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredSessionList(PREDICATE_SHOW_UPCOMING_WEEK_SESSIONS);
        return new CommandResult(Messages.MESSAGE_UPCOMING_WEEK_SESSIONS_OVERVIEW);
    }
}
