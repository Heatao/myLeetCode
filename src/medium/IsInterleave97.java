package medium;

public class IsInterleave97 {
    private boolean tag = false;
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length())
            return false;
        backtrack3string(s1, s2, s3, 0, 0, 0);
        return tag;
    }

    private void backtrack3string(String s1, String s2, String s3, int indexs1, int indexs2, int indexs3) {
        if (indexs1 == s1.length() && indexs2 == s2.length() && indexs3 == s3.length()){
            tag = true;
            return;
        }
        if (tag) return;

    }
}
