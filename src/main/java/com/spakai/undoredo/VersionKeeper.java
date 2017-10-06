/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spakai.undoredo;

public class VersionKeeper {
   String groupVersion;
   String subscriberVersion;
    
   public String getGroupVersion() {
       return groupVersion;
   }
   public String getSubscriberVerson() {
       return subscriberVersion;
   }
   
   public void setGroupVersion(String groupVersion) {
       this.groupVersion = groupVersion;       
   }
   
   public void setSubscriberVersion(String subscriberVersion) {
       this.subscriberVersion = subscriberVersion;       
   }
}
