package seedu.address.logic.commands.session;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.session.CliSyntax.PREFIX_PERIOD;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SESSIONS;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.function.Predicate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.session.Session;

/**
 * Finds and lists all Sessions in address book regardless of date and time.
 */
public class ViewSessionCommand extends Command {

    public static final String COMMAND_WORD = "sview";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the sessions shown in the Session List "
            + "to the period specified. "
            + "Possible periods -> 'week', 'all', 'future'.\n"
            + "Parameters: " + PREFIX_PERIOD + "PERIOD "
            + "Example: " + COMMAND_WORD + " " + PREFIX_PERIOD + "week ";
    public static final String MESSAGE_SHOW_SESSIONS_SUCCESS = "Session List updated with requested period!";

    public static final String VALID_WEEK_SESSIONS_PERIOD = "week";
    public static final String VALID_ALL_SESSIONS_PERIOD = "all";
    public static final String VALID_FUTURE_SESSIONS_PERIOD = "future";

    public static final Predicate<Session> PREDICATE_SHOW_UPCOMING_WEEK_SESSIONS = (session) ->
            session.getStartTime().isBefore(LocalDateTime.now().truncatedTo(DAYS).plusDays(7))
                    && session.getStartTime().isAfter(LocalDateTime.now().truncatedTo(DAYS));
    public static final Predicate<Session> PREDICATE_SHOW_FUTURE_SESSIONS = (session) ->
            session.getStartTime().isAfter(LocalDateTime.now().truncatedTo(DAYS));

    /**
     * Uses a hashmap to get the corresponding predicate for the period specified.
     */
    public static final HashMap<String, Predicate<Session>> PREDICATE_HASH_MAP = new HashMap<>();
    static {
        PREDICATE_HASH_MAP.put(VALID_WEEK_SESSIONS_PERIOD, PREDICATE_SHOW_UPCOMING_WEEK_SESSIONS);
        PREDICATE_HASH_MAP.put(VALID_FUTURE_SESSIONS_PERIOD, PREDICATE_SHOW_FUTURE_SESSIONS);
        PREDICATE_HASH_MAP.put(VALID_ALL_SESSIONS_PERIOD, PREDICATE_SHOW_ALL_SESSIONS);
    }
    private String period;

    public ViewSessionCommand(String period) {
        this.period = period;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Session> predToUse = PREDICATE_HASH_MAP.get(period);
        assert(predToUse != null);
        model.updateFilteredSessionList(predToUse);
        return new CommandResult(MESSAGE_SHOW_SESSIONS_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewSessionCommand // instanceof handles nulls
                && period.equals(((ViewSessionCommand) other).period)); // state check
    }
}
