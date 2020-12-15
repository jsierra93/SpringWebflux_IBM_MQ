package co.com.jsierra.webfluxibmmq.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Route {

    @Bean
    public RouterFunction<ServerResponse> router(Handler handler) {
        return route(
                POST("send").and(accept(MediaType.APPLICATION_JSON)), handler::send).andRoute(
                POST("send/topic").and(accept(MediaType.APPLICATION_JSON)), handler::sendTopic);
    }

}
