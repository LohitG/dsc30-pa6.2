import org.junit.*;
import static org.junit.Assert.*;
public class BSTreeTester {
    @Test
    public void basicBSTFindKey() {
        BSTree<String> test = new BSTree<String>();
        test.insert("lol");
        test.insert("bye");
        test.insert("dummy");
        test.insert("silly");
        assertTrue(test.findKey("bye"));
        assertTrue(test.findKey("lol"));
        assertTrue(test.findKey("dummy"));
        assertTrue(test.findKey("silly"));
    }

}
