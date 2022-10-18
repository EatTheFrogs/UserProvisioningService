package com.eatthefrog.UserProvisioningService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serial;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OktaRequest extends BaseModel {

    @Serial
    private static final long serialVersionUID = 852012434758894066L;

    private OktaRequestData data;
    private String eventTime;
}
