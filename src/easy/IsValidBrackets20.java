package easy;

import java.util.*;

/**
 * LeetCode20.有效的括号
 */
public class IsValidBrackets20 {
    /**
     * 注意一些小细节，比如只有左括号和只有右括号时，需要验证栈是否为空
     * @param s
     * @return
     */
    public boolean mySolution_isValid(String s) {
        Stack<Character> bracketStack = new Stack<>();
        boolean tag = true;
        for (int i = 0; i < s.length(); i++) {
            char thisChar = s.charAt(i);
            if (thisChar=='(' || thisChar=='[' || thisChar=='{'){
                //这里thisChar自动装箱
                bracketStack.push(thisChar);
            }
            else if (thisChar==')' || thisChar==']' || thisChar=='}'){
                if (bracketStack.isEmpty()) {
                    tag=false;
                    break;
                }
                //这里自动解封
                char stackChar = bracketStack.pop();
                switch (thisChar){
                    case ')':
                        if (stackChar!='(') tag=false;
                        break;
                    case ']':
                        if (stackChar!='[') tag=false;
                        break;
                    case '}':
                        if (stackChar!='{') tag=false;
                        break;
                }
            }
        }
        if (!bracketStack.isEmpty()) tag=false;
        return tag;
    }

    /**
     * 官方做法，用了一个小技巧，Hashmap来存储映射，可以转为用静态工厂方法处理这个集合
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    private boolean do2nd(String s) {
        if(s == null || s.length() == 0) return true;
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++) {
            char thisC = s.charAt(i);
            if(thisC == '(' || thisC == '{' || thisC == '[')
                stack.push(thisC);
            else {
                if(stack.isEmpty()) return false;
                char left = stack.pop();
                switch (thisC){                                         // 易错点1: swtich的语法别忘了括号
                    case ')':
                        if(left != '(') return false;
                        break;
                    case '}':
                        if(left != '{') return false;
                        break;
                    case ']':
                        if(left != '[') return false;
                        break;
                }
            }
        }
        if(!stack.isEmpty()) return false;                               // 易错点2:最后栈应该为空
        return true;
    }
}
