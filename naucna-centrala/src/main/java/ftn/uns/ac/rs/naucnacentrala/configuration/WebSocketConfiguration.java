//package ftn.uns.ac.rs.naucnacentrala.configuration;
//
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//
//
//@Configuration
//@ComponentScan("ftn.uns.ac.rs.naucnacentrala.businessrules.controller")
//public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {
//
////    @Override
////    public void configureMessageBroker(MessageBrokerRegistry config) {
////        config.enableSimpleBroker("/nc", "/nc/errors");
////        config.setApplicationDestinationPrefixes("/app");
////    }
////
////    @Override
////    public void registerStompEndpoints(StompEndpointRegistry registry) {
////        registry.addEndpoint("/nc")
////        .setAllowedOrigins("*");
////        //.withSockJS();
////    }
//}