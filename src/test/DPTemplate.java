package test;

/**
 * 思考过程：递归 -> 发现重复计划 -> 用记忆化的方式消除重复计划 -> 通过计算顺序，消除递归，用刷表的方式顺序计算
 * DP三要素：状态（变量和初始化），状态转移方程（for循环），边界（递推的结束条件）
 * DP的适用：一个最优策略的子策略也是最优的；无后效性；重复结构子问题
 * DP两大类：
 *     一种是求最优解类，典型问题是背包问题，
 *     另一种就是计数类，比如这里的统计方案数的问题，它们都存在一定的递推性质。
 *     前者的递推性质还有一个名字，叫做 「最优子结构」 ——即当前问题的最优解取决于子问题的最优解，
 *     后者类似，当前问题的方案数取决于子问题的方案数。所以在遇到求方案数的问题时，我们可以往动态规划的方向考虑。
 * DP的空间优化：滚动数组
 */
public class DPTemplate {
}
