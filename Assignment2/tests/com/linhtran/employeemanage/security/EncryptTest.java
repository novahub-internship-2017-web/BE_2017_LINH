package com.linhtran.employeemanage.security;

import org.junit.Test;
import static org.junit.Assert.*;

public class EncryptTest {
    @Test
    public void encryptPass() throws Exception {
        String password = "my password";
        String hash = Encrypt.encryptPass(password);
        assertTrue(Encrypt.verifyPassword(password, hash));
    }

}