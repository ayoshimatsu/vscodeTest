package com.yoshimatsu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesParameter {
   private String oUser;
   private String oServerIpAddress;
   private String oServerport;

   public PropertiesParameter(String aPropertyPass) {
      Properties properties = new Properties();
      String propertyPass = aPropertyPass;
      try {
         InputStream propertyStream = new FileInputStream(propertyPass);
         properties.load(propertyStream);
         oUser = properties.getProperty("user");
         oServerIpAddress = properties.getProperty("server_ip");
         oServerport = properties.getProperty("server_port");
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

   public String getServerPort() {
      return oServerport;
   }
}
