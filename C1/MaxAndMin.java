public class MaxAndMin {
    public static void main(String[] args) {
        int[] data = {313, 89, 123, 323, 313, 15, 90, 56, 39};
        int max = data[0];
        int min = data[0];
        for (int i : data) {
            if(i > max) max = i;
            if(i < min) min = i;
        }
        System.out.println("Max = " + max);
        System.out.println("Min = " + min);
    }
}