package tirocinio.curricolare;

import it.unibo.tirocinio.martelli.search.api.Searcher;
import it.unibo.tirocinio.martelli.search.impl.SearcherImpl;
import it.unibo.tirocinio.martelli.springboot.api.SearcherObserver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SearcherTest {
    public static class SearcherController implements SearcherObserver {
        private final List<String> elementList = new ArrayList<>();
        private final List<String> dataBreachesList = new ArrayList<>();
        @Override
        public List<String> getAllDataBaseElements() {
            return elementList;
        }

        public void addElement(final String url, final String value) {
            elementList.add("[" + url + "," + value + "]");
        }

        @Override
        public void addDataBreach(String url, String value, String type) {
            dataBreachesList.add(url + " " + value + " " + type);
        }

        public List<String> getDataBreachesList() {
            return dataBreachesList;
        }
    };

    @Test
    public void SearcherRun() {
        final SearcherController controller = new SearcherController();
        final Searcher searcher = new SearcherImpl(controller);
        searcher.addRegex("[a-z]{2}[0-9]{2}", "due lettere e due cifre");
        searcher.addRegex("[0-9]{4}", "numero a 4 cifre");
        controller.addElement("www.parole.com", "ds01");
        controller.addElement("www.parole.com", "Dsdsd");
        controller.addElement("www.numeri.com", "1258");
        controller.addElement("www.numeri.com", "12.5");
        searcher.run();
        assertEquals(2, controller.getDataBreachesList().size());
        searcher.controlElement("www.tutto.com", "rt5265");
        assertEquals(4, controller.getDataBreachesList().size());
        System.out.println(controller.getDataBreachesList()
                .stream()
                .reduce((s, t) -> s + "\n" + t)
                .orElse(""));

    }
}
