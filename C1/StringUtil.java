import java.util.Scanner;

public class StringUtil {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //获取String值
        String a = in.nextLine();
        String b = "";
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != ' '){
                System.out.println(b);
                b += a.charAt(a.charAt(i));
            }
        }
        System.out.println(b);
    }
}