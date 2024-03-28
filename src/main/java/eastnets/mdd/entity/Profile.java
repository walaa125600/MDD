package eastnets.mdd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import saa.entity.FinMessage;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile extends Main {

    private String name;
    private String description;
    private Authorization authorization;
    private Roles roles;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }


}
