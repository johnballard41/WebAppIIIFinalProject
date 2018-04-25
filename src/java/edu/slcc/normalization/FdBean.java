/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.normalization;

import java.util.ArrayList;
import java.util.Iterator;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author ASDV2
 */
@Named("fd")
@RequestScoped
public class FdBean
{

    final String TOKEN = "->";
    private MyArrayList fds;
    private String closure = "";

    private String result = "";
    private String error = "";

    /**
     * Creates a new instance of Normalization
     */
    public FdBean()
    {
        fds = new MyArrayList();
    
    }
    public void loadSampleFds()
    {
        fds.clear();
        fds.add("AB->C");
        fds.add("C->A");
        fds.add("BC->D");
        fds.add("ACD->B");
        fds.add("D->E");
        fds.add("D->G");
        fds.add("BE->C");
        fds.add("CG->B");
        fds.add("CG->D");
        fds.add("CE->A");
        fds.add("CE->G");      
    }
    
    public MyArrayList getFds()
    {
        return fds;
    }

    public void setFds(MyArrayList fds)
    {
        this.fds = fds;
    }

    public String getClosure()
    {
        return closure;
    }

    public void setClosure(String closure)
    {
        this.closure = closure;
    }

    public void clear()
    {
        fds.clear();
        closure = "";
        result = "";
        error ="";

    }

    public void calcClosure()
    {
        if (fds.size() > 0)
          {
            result = TheoryFD.closure(fds, closure, TOKEN);
          }
        else
          {
            error = "No FDs";
          }

    }

    public void calcNoRedundantAttributes()
    {
        if (fds.size() == 0)
          {
            error = "No FDs";
            return;
          }

        ArrayList<String> ar =  ( ArrayList<String>) fds.clone();
        ar = TheoryFD.eliminateRedundantAttibutes(ar, TOKEN);
        Iterator it = ar.iterator();
        String s = "";
        for (String ss : ar)
          {
            s += ss + "<br/>";
          }

        result = s;

    }

    public void calcNoRedundantFDs()
    {
        if (fds.size() == 0)
          {
            error = "No FDs";
            return;
          }
        ArrayList<String> ar =  ( ArrayList<String>) fds.clone();
         ar = TheoryFD.eliminateRedundantFDs(ar, TOKEN);
        Iterator it = ar.iterator();
        String s = "";
        for (String ss : ar)
          {
            s += ss + "<br/>";
          }

        result = s;

    }

    public void calcMinimalCover()
    {
        if (fds.size() == 0)
          {
            error = "No FDs";
            return;
          }
        ArrayList<String> ar =  ( ArrayList<String>) fds.clone();
        ar = TheoryFD.eliminateRedundantAttibutes(ar, TOKEN);
        ar = TheoryFD.eliminateRedundantFDs(ar, TOKEN);
        Iterator it = ar.iterator();
        String s = "";
        for (String ss : ar)
          {
            s += ss + "<br/>";
          }

        result = s;

    }

    public String getResult()
    {
        return result;
    }
    public String getError()
    {
        return error;
    }
    public void validateFD(FacesContext fc, UIComponent c, Object value)
    {
        MyArrayList ar = (MyArrayList) value;
        
        for (int i = 0; i < ar.size(); ++i)
          {
            String s = ar.get(i);
            //at least one occurance of Ato Z  followed by -> followed by a single capital letter
            String regX ="[A-Z]+->[A-Z]{1}";
           
              
            if (s.matches(regX) == false)
              {
                throw new ValidatorException(
                        new FacesMessage("FD *** " + s + " *** is invalid. Please type a valid FD. One FD per line. FD regX: [A-Z]+->[A-Z]{1}" ));
              }
          }
    }
}
