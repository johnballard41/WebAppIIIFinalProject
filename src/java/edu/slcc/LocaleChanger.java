package edu.slcc;

import java.util.Locale;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
public class LocaleChanger
{

    public void changeLocale(String languageCode)
    {
      
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(new Locale(languageCode));
    }
}
