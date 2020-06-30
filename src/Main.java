import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Stack<Integer> stack = new Stack<>();
        stack.add(2);
        stack.add(3);

        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack);
    }

}
