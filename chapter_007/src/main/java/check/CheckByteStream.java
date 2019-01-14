package check;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CheckByteStream {
    public boolean isNumber(InputStream in) throws IOException {
        int num;
        boolean result = false;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            num = Integer.valueOf(br.readLine());
            if (num % 2 == 0) {
                System.out.println("Your number is even: " + num);
                result = true;
            } else {
                System.out.println("Your number isn't even: " + num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
