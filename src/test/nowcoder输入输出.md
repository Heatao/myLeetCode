### 牛客网输入输出

ACM模式需要自己用Scanner写输入输出，其他模式可以自己的main里面写输入输出

nextInt()：直至读取到空格或回车之后结束本次的int值；
next()：直至读取到空格或回车之后结束本次的String值，不可读取回车；
nextLine()：直至读取到换行符（回车）之后结束本次读取的String，可读取回车（空值）。

```Java
// import的部分应该也要写进入
import java.util.Scanner;

// 一定要是Main 
public class Main{
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        while(in.hasNext()){
            int a=in.nextInt();
            int b=in.nextInt();

            // 最后答案需要打印出来
            System.out.println(a+b);
        }
    }
}
```

```Java
// 一行有多个信息 split切分
// a c bb 一直输入
import java.util.*;
        import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String[] strs = sc.nextLine().split(" ");
            // 操作
            System.out.println(Arrays.toString(strs));
            // 记得跳出喔
        }
    }
}
```