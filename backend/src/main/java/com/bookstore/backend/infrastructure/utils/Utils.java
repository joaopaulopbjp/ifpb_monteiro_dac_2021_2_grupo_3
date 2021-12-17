package com.bookstore.backend.infrastructure.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;

import org.springframework.beans.BeanWrapper;
import java.util.Set;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
    
        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
    
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public String shar256(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest =  MessageDigest.getInstance("SHA-256");
        messageDigest.update(senha.getBytes("UTF-8"));
        return new BigInteger(1, messageDigest.digest()).toString(16);
    }
}
