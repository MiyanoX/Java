class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int left = 0, right = matrix.length - 1; // !
        while (left <= right) { // !
            int mid = (left + right) / 2; // !
            if (matrix[mid][0] == target) { // !
                return true;
            } else if (matrix[mid][0] < target) {
                left = mid + 1; // !
            } else {
                right = mid - 1; // !
            }
        }
        if (right < 0) return false;
        int i = right;
        left = 0;
        right = matrix[i].length - 1; // !
        while (left <= right) { // !
            int mid = (left + right) / 2; // !
            if (matrix[i][mid] == target) { // !
                return true;
            } else if (matrix[i][mid] < target) {
                left = mid + 1; // !
            } else {
                right = mid - 1; // !
            }
        }
        return false;
    }
}
