class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int left = 0, right = x - 1; // !
        int res = 0;
        while (left <= right) { // !
            int mid = (left + right) / 2; // !
            if ((long) mid * mid <= x) { // !
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1; // !
            }
        }
        return res;
    }
}
