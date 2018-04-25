/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.normalization;

import java.util.ArrayList;

public class MyArrayList extends ArrayList<String>
{

    @Override
    public String toString()
    {
        Object[] ar = super.toArray();
        String sArray = "";
        for (Object s : ar)
          {
            sArray += s.toString() + "\n";
          }

        return sArray;
    }

}
