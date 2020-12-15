package co.com.jsierra.webfluxibmmq.controllers;

import co.com.jsierra.webfluxibmmq.MqService;
import co.com.jsierra.webfluxibmmq.models.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;

@Slf4j
@Configuration
public class Handler {

    @Autowired
    MqService mqService;

     public Mono<ServerResponse> send(ServerRequest serverRequest) {
         Mono<String> message = serverRequest.body(toMono(Message.class))//toMono es un extractor
                            .flatMap( msg ->  mqService.sendMessage(msg.getMessage()));
         return ServerResponse
                     .ok()
                     .body(message, String.class);
         }

    public Mono<ServerResponse> sendTopic(ServerRequest serverRequest) {
        Mono<String> message = serverRequest.body(toMono(Message.class))//toMono es un extractor
                .flatMap( msg ->  mqService.sendMessageTopic(msg.getMessage()));
        return ServerResponse
                .ok()
                .body(message, String.class);
    }
}
