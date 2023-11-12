import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HelloTest {

    static final Logger logger = LoggerFactory.getLogger(HelloTest.class);

    Hello fixture;

    @BeforeEach
    void setUp() {

        logger.info("setting up");
        fixture = new Hello();

    }

    @AfterEach
    void tearDown() {
        fixture = null;
    }

    @Test
    void main() {
        fixture.main(null);
    }
}