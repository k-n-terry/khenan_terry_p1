package dev.terry.utilities;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UniqueIdMD5{
    StringBuilder uniqueString = new StringBuilder("");

    public UniqueIdMD5(){
    }
    public String makeUniqueId(String a, String b){
        this.uniqueString.append(a);
        this.uniqueString.append(b);
        MessageDigest messageDigest = null;
        // Again it would be unwise to use MD5 for real applications, as MD5 has unsatisfactory
        // collision-resistance. I am using it as a demonstration.
        try{
            messageDigest = MessageDigest.getInstance("MD5");
            // update messageDigest object to hold the bytes of the password string
            messageDigest.update(this.uniqueString.toString().getBytes());
            // assign the messageDigest object to a digest variable as a byte array
            byte[] messageDigestBytes = messageDigest.digest();
            // convert bytes from digest into hexadecimal, and then convert into string.
            String uniqueId = DatatypeConverter.printHexBinary(messageDigestBytes).toUpperCase();
            return uniqueId;
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            String uniqueId = null;
            return uniqueId;
        }
    }
    public String makeUniqueId(String a, String b, String c){
        this.uniqueString.append(a);
        this.uniqueString.append(b);
        this.uniqueString.append(c);
        MessageDigest messageDigest = null;
        try{
            messageDigest = MessageDigest.getInstance("MD5");
            // update messageDigest object to hold the bytes of the password string
            messageDigest.update(this.uniqueString.toString().getBytes());
            // assign the messageDigest object to a digest variable as a byte array
            byte[] messageDigestBytes = messageDigest.digest();
            // convert bytes from digest into hexadecimal, and then convert into string.
            String uniqueId = DatatypeConverter.printHexBinary(messageDigestBytes).toUpperCase();
            return uniqueId;
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            String uniqueId = null;
            return uniqueId;
        }
    }
}
