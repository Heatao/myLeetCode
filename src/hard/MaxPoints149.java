package hard;

import java.util.HashMap;

/**
 * 这个题没去做，虽然想到了枚举任意两个点，但是没有想到用一个新的Pair类来表示点
 * （主要是看了CodeTop发现这道题基本没有被考过hhhh）
 *
 * 下面是抄的花花酱的评论区，然而还是错的orz，有空再修改吧
 */
public class MaxPoints149 {
    class Pair {
        int x;
        int y;
        public Pair(int a, int b) {
            x = a;
            y = b;
        }
    }

    public int maxPoints(int[][] points) {
        int n = points.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            HashMap<Pair, Integer> cnt = new HashMap<>();
            int same_points = 1;  // # of points with same coordinates
            int max_points = 0;
            Pair p1 = new Pair(points[i][0], points[i][1]);
            for (int j = i+1; j < n; j++) {
                if(points[i][0] == points[j][0] && points[i][1] == points[j][1])
                    same_points++;
                else {
                    Pair p2 = new Pair(points[j][0], points[j][1]);
                    Pair slope = getSlope(p1, p2);
                    if (!cnt.containsKey(slope))
                        cnt.put(slope, 2);
                    else
                        cnt.put(slope, cnt.get(slope) + 2);
                    max_points = Math.max(max_points, cnt.get(slope));
                }
            }
            ans = Math.max(ans, max_points + same_points);
        }
        return ans;
    }

    private Pair getSlope(Pair p1, Pair p2) {
        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;
        // horizontal
        if (dy == 0) return new Pair(p1.y, 0);
        // vertical
        if (dx == 0) return new Pair(0, p1.x);
        int d = gcd(dx, dy);
        return new Pair(dy/d, dx/d);
    }

    private int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m%n);
    }
}
