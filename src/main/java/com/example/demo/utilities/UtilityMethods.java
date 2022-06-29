package com.example.demo.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilityMethods {

    public static Boolean validateReg(String commitMessage){
        String regex = ".*(:).*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(commitMessage);
        return m.matches();
    }

}
