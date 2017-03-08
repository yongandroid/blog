import java.util.ArrayList;
import java.util.List;

/**
 *
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * Created by SeanWu on 2017/3/6.
 */
public class HeapOOM {

    /**
     * this class will result OutOfMemoryError : Java heap space
     */
    static class TestModel {
    }

    /**
     * this class will result OutOfMemoryError : GC overhead limit exceeded
     */
    static class TestModel2 {
        double d = 1.1;
    }
    public static void main(String[] args) {

        List<TestModel> list = new ArrayList<>();
        while (true){
            list.add(new TestModel());
        }
    }
}
