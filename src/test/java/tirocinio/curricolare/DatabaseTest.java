package tirocinio.curricolare;

import it.unibo.tirocinio.martelli.database.api.Database;
import it.unibo.tirocinio.martelli.database.impl.DatabaseSpark;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest
{
    Database database = new DatabaseSpark();
    @BeforeEach
    public void before() {

    }
    @Test
    public void addElementUT() {
        assertEquals(0, database.getAllElements().size());
        database.addElement("one", "oneT");
        assertEquals(1, database.getAllElements().size());
        System.out.println(database.getAllElements().get(0));
        assertEquals(List.of("[one,oneT]"), database.getAllElements());
    }
}
