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
   
   public VersionKeeper updateGroupVersion(String groupVersion) {
       this.groupVersion = groupVersion;
       return this;
   }
   
   public VersionKeeper setSubscriberVersion(String subscriberVersion) {
       this.subscriberVersion = subscriberVersion;
       return this;
   }
    
}
