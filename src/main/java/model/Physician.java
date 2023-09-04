package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "physician")
public class Physician {

    @Id
    private String id;
    private String name;
    private String surname;
    private String title;
    private LocalDateTime dateOfBirth;
}
