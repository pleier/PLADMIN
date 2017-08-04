package com.github.pleier.util.mail;
/**
 * �����ʼ���Ҫʹ�õĻ�����Ϣ
* @author plei
*
* @version 2.0
 */
import javax.mail.*;

public class MyAuthenticator extends Authenticator{   
    String userName=null;   
    String password=null;   
        
    public MyAuthenticator(){   
    }   
    public MyAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }   
}   
