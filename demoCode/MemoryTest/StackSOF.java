/**
 * VM Args: -Xss128k
 * Created by SeanWu on 2017/3/8.
 */
public class StackSOF {
    private int stackLength = 1;

    void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackSOF stackSOF = new StackSOF();
        try {
            // this statement java.lang.StackOverflowError
            stackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println(stackSOF.stackLength);
            e.printStackTrace();
        }
    }

}
