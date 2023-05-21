package hcmute.edu.vn.finalproject20110451.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDetail {
    private String token;

    private long expired;

    private List<String> roles = new ArrayList<>();
}

