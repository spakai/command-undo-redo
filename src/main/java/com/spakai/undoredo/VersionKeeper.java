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
