package org.noctisdev.sciapi.web.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter @Setter @Builder
public class BaseResponse {

    private Object data;
    private String message;
    private Boolean success;
    private Integer status;
    private HttpStatus httpStatus;

    public ResponseEntity<BaseResponse> apply() {
        return new ResponseEntity<>(this, this.getHttpStatus());
    }

}
