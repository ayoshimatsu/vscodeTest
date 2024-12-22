package com.yoshimatsu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesParameter {
   String oUser;
   String oServerIpAddress;

   public PropertiesParameter(String aPropertyPass) {
      Properties properties = new Properties();
      String propertyPass = aPropertyPass;
      try {
         InputStream propertyStream = new FileInputStream(propertyPass);
         properties.load(propertyStream);
         oUser = properties.getProperty("user");
         oServerIpAddress = properties.getProperty("server_ip");
      } catch (IOException e) {
         System.out.println("Error : Fail to read property file.");
         e.printStackTrace();
      }
   }

   public String getUser() {
      return oUser;
   }

   public String getServerIpAddress() {
      return oServerIpAddress;
   }
}
