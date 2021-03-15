package test;

/**
 * 记忆化递归模版
 * 本质上就是用一个数组把历史求解（子问题）记录下来的递归
 *
 * m = [0] * max_size # 开辟一个数组来存储，或者使用字典
 * def f(n):
 *     if n <= 1:
 *         return n
 *     if m[n] <= 0:
 *         m[n] = f(n - 1) + f(n -2)
 *     return m[n]
 *
 * 涉及题目：62
 */
public class MemoizedRecursion {
}
