package ru.levelup.at.java.homework9.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    private UserData data;
}

