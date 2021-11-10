package apap.tutorial.emsidi.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgePrediction {
    @JsonProperty("nama")
    private String nama;

    @JsonProperty("umur")
    private Integer umur;

    @JsonProperty("count")
    private Integer count;
}
