import com.example.tastebud.data.User
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class UserTest {
    private lateinit var user1: User
    private lateinit var user2: User
    private lateinit var user3: User
    private lateinit var user4: User

    @Before
    fun setUp() {
        user1 = User(
            "1",
            "test1@example.com",
            "John Doe",
            listOf("Vegetarian", "Gluten-Free"),
            120,
            5,
            10
        )

        user2 = User(
            "2",
            "test2@example.com",
            "Jane Smith",
            listOf("Vegan", "Low-Carb"),
            180,
            8,
            15
        )

        user3 = User(
            "3",
            "test3@example.com",
            "Alice Johnson",
            listOf("Keto", "Paleo"),
            240,
            10,
            20
        )

        user4 = User(
            "4",
            "test4@example.com",
            "Bob Brown",
            listOf("Pescatarian", "Dairy-Free"),
            300,
            12,
            25
        )
    }

    @Test
    fun getUserId() {
        if (::user1.isInitialized) {
            assertEquals("1", user1.userId)
        }

        if (::user2.isInitialized) {
            assertEquals("2", user2.userId)
        }

        if (::user3.isInitialized) {
            assertEquals("3", user3.userId)
        }

        if (::user4.isInitialized) {
            assertEquals("4", user4.userId)
        }
    }

    @Test
    fun getEmail() {
        if (::user1.isInitialized) {
            assertEquals("test1@example.com", user1.email)
        }

        if (::user2.isInitialized) {
            assertEquals("test2@example.com", user2.email)
        }

        if (::user3.isInitialized) {
            assertEquals("test3@example.com", user3.email)
        }

        if (::user4.isInitialized) {
            assertEquals("test4@example.com", user4.email)
        }
    }

    @Test
    fun getFullName() {
        if (::user1.isInitialized) {
            assertEquals("John Doe", user1.fullName)
        }

        if (::user2.isInitialized) {
            assertEquals("Jane Smith", user2.fullName)
        }

        if (::user3.isInitialized) {
            assertEquals("Alice Johnson", user3.fullName)
        }

        if (::user4.isInitialized) {
            assertEquals("Bob Brown", user4.fullName)
        }
    }

    @Test
    fun getDietaryRestrictions() {
        if (::user1.isInitialized) {
            assertEquals(listOf("Vegetarian", "Gluten-Free"), user1.dietaryRestrictions)
        }

        if (::user2.isInitialized) {
            assertEquals(listOf("Vegan", "Low-Carb"), user2.dietaryRestrictions)
        }

        if (::user3.isInitialized) {
            assertEquals(listOf("Keto", "Paleo"), user3.dietaryRestrictions)
        }

        if (::user4.isInitialized) {
            assertEquals(listOf("Pescatarian", "Dairy-Free"), user4.dietaryRestrictions)
        }
    }

    @Test
    fun getMinsCooked() {
        if (::user1.isInitialized) {
            assertEquals(120, user1.minsCooked)
        }

        if (::user2.isInitialized) {
            assertEquals(180, user2.minsCooked)
        }

        if (::user3.isInitialized) {
            assertEquals(240, user3.minsCooked)
        }

        if (::user4.isInitialized) {
            assertEquals(300, user4.minsCooked)
        }
    }

    @Test
    fun getCompletedCount() {
        if (::user1.isInitialized) {
            assertEquals(5, user1.completedCount)
        }

        if (::user2.isInitialized) {
            assertEquals(8, user2.completedCount)
        }

        if (::user3.isInitialized) {
            assertEquals(10, user3.completedCount)
        }

        if (::user4.isInitialized) {
            assertEquals(12, user4.completedCount)
        }
    }

    @Test
    fun getStartedCount() {
        if (::user1.isInitialized) {
            assertEquals(10, user1.startedCount)
        }

        if (::user2.isInitialized) {
            assertEquals(15, user2.startedCount)
        }

        if (::user3.isInitialized) {
            assertEquals(20, user3.startedCount)
        }

        if (::user4.isInitialized) {
            assertEquals(25, user4.startedCount)
        }
    }
}
