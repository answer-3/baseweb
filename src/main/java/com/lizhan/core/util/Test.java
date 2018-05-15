package com.lizhan.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
    private static  Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {


        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();

        String key = "PJMFKPMHU3D4SOB4";//googleAuthenticator.generateSecretKey();

        System.out.println(googleAuthenticator.generateSecretKey());

        System.out.println(GoogleAuthenticator.getQRBarcodeURL("李展","http://www.lizhan.com",key));


        boolean b = googleAuthenticator.check_code("RIQM6EVNSMGVZ4QAEQD3VLMZXIAIGYYOCUYDIOTSJVFVU36UNYMA====",70359);


        System.out.println(b);

        logger.info("哈哈哈哈");
    }

}
