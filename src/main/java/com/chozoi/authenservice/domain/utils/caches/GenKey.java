package com.chozoi.authenservice.domain.utils.caches;

public class GenKey {

      public static final String PREFIX = "cz";

      public static String genTokenKey(String token)
      {
        return PREFIX + ":token:" + token;
      }

}
