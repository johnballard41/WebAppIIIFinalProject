/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.normalization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ASDV2
 */
@FacesConverter(value="fdConverter")
public class ConverterFD
        implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {

     MyArrayList fds = new MyArrayList();
    if ( value.length() == 0 )
        return fds;
     String[] ar = value.split("\r\n");
     List<String>  list = Arrays.asList(ar);
     Iterator it = list.iterator();
     while( it.hasNext())
         fds.add( (String) it.next() );
        return fds;
     }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        return value.toString();
    }
    
}
