import java.util.Scanner;

public class InputTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] a = new String[100];
        for(int i = 0;;i++){
            String b = in.nextLine();
            a[i] = b;
            if(b.equals("end")) break;
        }
        for(String p : a) {
            if(p.equals("end")) break;
            System.out.println(p);
        }
    }
}