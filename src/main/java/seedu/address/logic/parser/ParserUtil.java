package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attribute.Address;
import seedu.address.model.attribute.Email;
import seedu.address.model.attribute.Field;
import seedu.address.model.attribute.Name;
import seedu.address.model.attribute.Phone;
import seedu.address.model.group.Group;
import seedu.address.model.group.Path;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    private static final String PATH_VALIDATION_REGEX = "([a-zA-Z0-9_-]+\\/?)+([a-zA-Z0-9_-]+)";
    private static final String PERSON_NAME_PATTERN = "[A-Za-z][a-zA-Z \\-]*";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing
     * whitespaces will be trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}. Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code String path} into a {@code Group}. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @param path to the currently nested group.
     * @return a Path object that specifies a group based on that path.
     * @throws ParseException if the given {@Code path} is not valid.
     */
    public static Path parsePath(String path) throws ParseException {
        requireNonNull(path);
        String trimmedPath = path.trim();
        if (!isValidPath(trimmedPath)) {
            throw new ParseException(Path.MESSAGE_CONSTRAINTS);
        }
        return new Path(trimmedPath);
    }

    /**
     * Parses {@code String group} into a {@code Group}.
     *
     * @param group name of group that is currently being accessed.
     * @return a Group object that refers to a specified team.
     * @throws ParseException if the given {@Code Group} is not valid.
     */
    public static Group parseGroup(String group) throws ParseException {
        requireNonNull(group);
        if (!Group.isValidGroupName(group)) {
            throw new ParseException(Group.MESSAGE_CONSTRAINTS);
        }

        return new Group(group);
    }

    /**
     * Parses {@code String field} into a {@code Field}.
     *
     * @param fieldName the name of the field that is to be created.
     * @return A Field instance containing the name of the field.
     * @throws ParseException if the field name is invalid.
     */
    public static Field parseField(String fieldName) throws ParseException {
        requireNonNull(fieldName);
        if (!Field.isValidField(fieldName)) {
            throw new ParseException(Field.MESSAGE_CONSTRAINTS);
        }
        return new Field(fieldName);
    }

    /**
     * Parses person name. Person name is more strict than Name thus this works
     */
    public static Name parsePersonName(String name) throws ParseException {
        requireNonNull(name);
        name = name.replaceAll("\\s+", " ").trim();
        if (!name.matches(PERSON_NAME_PATTERN)) {
            throw new ParseException(Person.MESSAGE_INVALID_PERSON_NAME);
        }
        return new Name(name);
    }

    /**
     * Checks if the path is valid. Only alphanumeric, hyphen, underscore and slash are allowed.
     *
     * @param path to reach the current AbstractContainerItem.
     * @return true if path is valid, false otherwise.
     */
    public static boolean isValidPath(String path) {
        return path.matches(PATH_VALIDATION_REGEX);
    }



    /**
     * Splits the str by "|"
     */
    public static Pair splitPipe(String str) {
        String[] userInputs = str.trim().split("\\s*\\|\\s*", 2);
        String strFirst = userInputs[0];
        String strSecond = "";

        if (userInputs.length == 2) {
            strSecond = userInputs[1];
        }
        return Pair.of(strFirst, strSecond);
    }

    /**
     * Static class to represent a Pair
     */
    public static class Pair {
        private String first;
        private String second;

        private Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        static Pair of(String a, String b) {
            return new Pair(a, b);
        }

        @Override
        public String toString() {
            return String.format("%s, %s", first, second);
        }

        public String getFirst() {
            return first;
        }

        public String getSecond() {
            return second;
        }
    }
}
