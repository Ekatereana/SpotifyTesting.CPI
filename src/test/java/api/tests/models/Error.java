package api.tests.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonRootName("error")
public class Error {

    private int status;
    private String message;

    public Error(int i, String s) {
        status = i;
        message = s;
    }
}
