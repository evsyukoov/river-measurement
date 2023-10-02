package ru.bot.evsukov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Client")
public class Client {

    @Id
    private String telegramId;
    private String name;
    private String nickname;
    private String state;
}
