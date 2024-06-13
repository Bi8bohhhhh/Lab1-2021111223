import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DgraphTest {
    private Dgraph graph;

    @Before
    public void setUp() {
        // 在每个测试方法执行前初始化图
        graph = Main.createDirectedGraph("test.txt");
    }

    @Test
    public void testQueryBridgeWords_WordNotInGraph1() {
        String word1 = "abc";
        String word2 = "def";
        String expected = "No \"abc\" and \"def\" in the graph!";
        String actual = graph.queryBridgeWords(word1, word2);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryBridgeWords_WordNotInGraph2() {
        String word1 = "abc";
        String word2 = "explore";
        String expected = "No \"abc\" in the graph!";
        String actual = graph.queryBridgeWords(word1, word2);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryBridgeWords_WordNotInGraph3() {
        String word1 = "explore";
        String word2 = "abc";
        String expected = "No \"abc\" in the graph!";
        String actual = graph.queryBridgeWords(word1, word2);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryBridgeWords_NoBridgeWord() {
        String word1 = "to";
        String word2 = "new";
        String expected = "No bridge words from \"to\" to \"new\"!";
        String actual = graph.queryBridgeWords(word1, word2);
        assertEquals(expected, actual);
    }

    @Test
    public void testQueryBridgeWords_ExistingBridgeWord() {
        String word1 = "strange";
        String word2 = "civilizations";
        String expected = "The bridge words from \"strange\" to \"civilizations\" are:new,";
        String actual = graph.queryBridgeWords(word1, word2);
        assertEquals(expected, actual);
    }
}