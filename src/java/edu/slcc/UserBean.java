package edu.slcc;

import java.io.Serializable;

import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

@Named("user")
@SessionScoped
public class UserBean implements Serializable
{

    private String name;
    private String password;

    public String getName()
    {
        return name;
    }

    public void setName(String newValue)
    {
        name = newValue;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String newValue)
    {
        password = newValue;
    }
}
