import org.junit.Test;
import static org.junit.Assert.assertEquals;



public class MainTest {
    @Test
    public void testCreateDirectedGraph() {
        String fileName = "test3.txt";
        // 调用createDirectedGraph()方法获取result字符串
        Dgraph graph = Main.createDirectedGraph(fileName);
        String result = graph.show();

        // 定义预期的字符串值
        String expected1 = "to: explore-1\n" +
                "explore: strange-1\n" +
                "strange: world-1\n" +
                "world: null\n";
        String expected2 = "seek: out-1\n" +
                "out: life-1\n" +
                "life: civilizations-1\n" +
                "civilizations: null\n";
        String expected3 = "world: and-1\n" +
                "and: life-1\n" +
                "life: null\n" ;
        String expected4 = "arm: strong-1\n" +
                "strong: yes-1\n" +
                "yes: null\n" ;
        // 使用assertEquals()断言方法比较result与预期值是否相同
        assertEquals(expected3, result);
    }
}

