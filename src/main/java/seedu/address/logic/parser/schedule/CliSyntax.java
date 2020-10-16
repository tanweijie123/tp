package seedu.address.logic.parser.schedule;

import seedu.address.logic.parser.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {
    /* Prefix definitions */
    public static final Prefix PREFIX_CLIENT_INDEX = new Prefix("c/");
    public static final Prefix PREFIX_SESSION_INDEX = new Prefix("s/");
    public static final Prefix PREFIX_IS_PAID = new Prefix("p/");
    public static final Prefix PREFIX_UPDATED_SESSION_INDEX = new Prefix("us/");
}
