package api.tests.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Error {

    private int status;
    private String message;

    public Error(int i, String s) {
        status = i;
        message = s;
    }
}
