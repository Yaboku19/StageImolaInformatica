package tirocinio.curricolare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.unibo.tirocinio.martelli.database.api.Database;
import it.unibo.tirocinio.martelli.database.impl.DatabaseSpark;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppTest 
{
    Database database;
    @BeforeEach
    public void before() {
        database = new DatabaseSpark();
    }
    @Test
    public void addElementUT() {
        database = new DatabaseSpark();
        assertEquals(0, database.getAllElements().size());
        database.addElement("cc", "aa");
        assertEquals(1, database.getAllElements().size());
    }
}
