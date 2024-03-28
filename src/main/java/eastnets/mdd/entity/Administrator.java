package eastnets.mdd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Administrator extends Main {

    private String filtrationCriteria;

    public String getFiltrationCriteria() {
        return filtrationCriteria;
    }

    public void setFiltrationCriteria(String filtrationCriteria) {
        this.filtrationCriteria = filtrationCriteria;
    }

    public String getFiltrationValue() {
        return filtrationValue;
    }

    public void setFiltrationValue(String filtrationValue) {
        this.filtrationValue = filtrationValue;
    }

    private String filtrationValue;


}
