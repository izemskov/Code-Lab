package ru.develgame.spring.async.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LongTermTask {
    private String id;
    private int status = 0;
}
