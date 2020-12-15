package co.com.jsierra.webfluxibmmq.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Message {
    String message;
}