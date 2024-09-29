package com.basic;

import org.testng.annotations.Test;

import java.util.Base64;

public class EncodingTest {

    @Test(description = "Its just testcase for how base 64 encoding works")
    public void base64Encoding() {
        String usernameAndpassword = "myUserName:Password";
        String base64Encode = Base64.getEncoder().encodeToString(usernameAndpassword.getBytes());
        System.out.println("Encoded =" + base64Encode);
        byte[] decodeBytes = Base64.getDecoder().decode(base64Encode);
        System.out.println("Decode = " + new String(decodeBytes));
    }

}
