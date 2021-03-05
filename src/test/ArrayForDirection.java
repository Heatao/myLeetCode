package test;

/**
 * 刷54题螺旋矩阵的时候发现方向很容易搞错，或者写很长的代码把自己搞晕
 * 看官方题解用二维数组表示方向，很简洁巧妙
 * 并且在874题，机器人中也可以这样做
 * mark一下
 * https://leetcode-cn.com/problems/walking-robot-simulation/solution/dong-nan-xi-bei-gao-bu-qing-yong-er-wei-shu-zu-jiu/
 * https://leetcode-cn.com/problems/spiral-matrix/solution/luo-xuan-ju-zhen-by-leetcode-solution/
 *
 */
public class ArrayForDirection {
    public void solutions() {
        //这里的directions的含义可以是：
        //                   北
        //                   0(0,1)
        //                   |
        //(左)西(-1,0) 3 ——  —— 1(1,0) 东（右）
        //                   |
        //                   2(0,-1)
        //                   北
        //
        //表示方位是上面的形式，但directions也可以表示二维数组遍历的方向：
        //此时，{0, 1}表示横坐标不变，向列坐标前进，即可以理解为向"左"，与表示方位完全不一样
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        //在表示方向时，directionIndex
        //在二维数组前进中，directionIndex
        int directionIndex = 0;

        //初始方位
        int row = 0;
        int column = 0;

        for (;;) {
            //达成某种添加后转向
            if (true) {
                //directionIndex+1表示右转，+3表示左转
                directionIndex = (directionIndex + 1) % 4;
            }

            //更新新的位置
            //对于表示北东南西方向而言，row/x只与第一维有关，col/y只与第二维有关
            //对于在二维数组中，一行向前向后只与第一维有关，一列向上向下只与第二维有关
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];

        }
    }
}
