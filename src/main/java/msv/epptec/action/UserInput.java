package msv.epptec.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Pro vstup aplikace - ted jen systemovy
 */
public class UserInput {

    public String getInput(String title) throws IOException {
        System.out.println(title);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

}
