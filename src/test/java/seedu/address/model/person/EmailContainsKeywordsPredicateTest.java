package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy = new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different person -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("AliceBob@mail.com").build()));

        // Multiple keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("AliceBob@mail.com").build()));

        // Only one matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("AliceCarol@mail.com").build()));

        // Mixed-case keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("AliceBob@mail.com").build()));

        // Similar email keywords
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("Shaun"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("ShawnKoh@mail.com").build()));
        assertTrue(predicate.test(new PersonBuilder().withEmail("ShawnyLim@mail.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withEmail("Alicetest@mail.com").build()));

        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("AliceBob@mail.com").build()));

        // Keywords match phone, name, remark and address, but does not match email
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("12345", "Street", "Gordon", "Researcher"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("12345").withName("Alice Gordon")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("Researcher").build()));
    }

    @Test void test_emailComparatorSort_compare() {
        Comparator<Person> comparator = new EmailContainsKeywordsPredicate(Collections.singletonList("Shaun"));

        // Sort by similarity
        Person p1 = new PersonBuilder().withEmail("ShawnKoh@mail.com").build();
        Person p2 = new PersonBuilder().withEmail("ShaunLim@mail.com").build();
        assertTrue(comparator.compare(p1, p2) > 0);

        // Sort lexicographically
        Person p3 = new PersonBuilder().withEmail("ShawnyLim@mail.com").build();
        Person p4 = new PersonBuilder().withEmail("ShawnKoh@mail.com").build();
        assertTrue(comparator.compare(p3, p4) > 0);
    }
}
