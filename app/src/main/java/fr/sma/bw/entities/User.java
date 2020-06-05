package fr.sma.bw.entities;

import java.io.Serializable;

public class User implements Serializable {
	
	
    private static final long serialVersionUID = -5230559201840363746L;
	
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    
    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
