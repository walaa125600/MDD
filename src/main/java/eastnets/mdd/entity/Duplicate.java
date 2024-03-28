package eastnets.mdd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import saa.entity.FinMessage;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Duplicate extends Main {

    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCreationDateFrom() {
        return creationDateFrom;
    }

    public void setCreationDateFrom(String creationDateFrom) {
        this.creationDateFrom = creationDateFrom;
    }

    public String getCreationDateTo() {
        return creationDateTo;
    }

    public void setCreationDateTo(String creationDateTo) {
        this.creationDateTo = creationDateTo;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String unit;
    private String creationDateFrom;
    private String creationDateTo;
    private String format;
    private String identifier;
    private String status;

    public FinMessage getFinMessage() {
        return finMessage;
    }

    public void setFinMessage(FinMessage finMessage) {
        this.finMessage = finMessage;
    }

    private FinMessage finMessage;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;



}
