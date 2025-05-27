module greeting.module {
    // Spring dependencies
 
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
    requires spring.core;
    requires spring.beans;
    requires spring.webmvc;
    
    
    
    // Export public packages
    exports com.greeting;
    exports com.greeting.shared;
    
    // Service provider
    provides com.greeting.shared.SharedGreetingService 
        with com.greeting.sharedimpl.SharedGreetingServiceImpl;
    
    // Open packages for Spring reflection
    opens com.greeting to spring.core, spring.context;
    opens com.greeting.controller to spring.core, spring.context;
    opens com.greeting.internal to spring.core, spring.context;
    opens com.greeting.internalimpl to spring.core, spring.context;
    opens com.greeting.shared to spring.core, spring.context;
    opens com.greeting.sharedimpl to spring.core, spring.context;
    opens com.greeting.exception to spring.core, spring.context;
}