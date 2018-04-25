package edu.slcc;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;

@Named("mp")
@SessionScoped
public class MainPane implements Serializable
{

    private int index;
    private int secondIndex;
    private static final int NEWS_INDEX = 0;
    private static final int STUDENT_INDEX = 1;
    private static final int ADMIN_INDEX = 2;
    private static final int PROMOTIONS_INDEX = 3;
    private static final int CONTACT_INDEX = 4;
    private static final int DATABASE_INDEX = 5;
    private static final int PAYMENT_INDEX = 6;
    private static final int FD_CURRENT_INDEX = 7;
    private static final int FD_ALUMNI_INDEX = 8;
    private static final int FD_COURSE_INDEX = 9;

    private String[] tabTooltips =
      {
        "tooltipNews", "tooltipStudents", "tooltipAdmin",
        "tooltipPromotions", "tooltipContact", "tooltipFlags" , "tooltipGreekFlag" , "tooltipUSFlag",
         "tooltipFlags"
      };

    public MainPane()
    {
        index = NEWS_INDEX;
    }

    // action listeners that set the current tab
    
       // action listeners that set the current tab
    public void newsAction(ActionEvent e)
    {
        index = NEWS_INDEX;
        secondIndex = 0;
    }

    public void databaseAction(ActionEvent e)
    {
        index = DATABASE_INDEX;
        secondIndex = 0;
    }
    
    public void studentAction(ActionEvent e)
    {
        index = STUDENT_INDEX;
        secondIndex = 0;
    }

    public void adminAction(ActionEvent e)
    {
        index = ADMIN_INDEX;
        secondIndex = 0;
    }

    public void contactAction(ActionEvent e)
    {
        index = CONTACT_INDEX;
        secondIndex = 0;
    }

    public void promotionsAction(ActionEvent e)
    {
        index = PROMOTIONS_INDEX;
        secondIndex = 0;
    }
    
    public void paymentAction(ActionEvent e)
    {
        index = PAYMENT_INDEX;
        secondIndex = 0;
    }
    
    public void curentStudentAction(ActionEvent e)
    {
        secondIndex = FD_CURRENT_INDEX;
    }
    
    public void alumniStudentAction(ActionEvent e)
    {
        secondIndex = FD_ALUMNI_INDEX;
    }
    
    public void courseAction(ActionEvent e)
    {
        secondIndex = FD_COURSE_INDEX;
    }

    private String getCSS(int forIndex)
    {
        if (forIndex == index)
          {
            return "tabbedPaneTextSelected";
          }
        else
          {
            return "tabbedPaneText";
          }
    }

    // CSS styles
    public String getNewsStyle()
    {
        return getCSS(NEWS_INDEX);
    }

    public String getStudentStyle()
    {
        return getCSS(STUDENT_INDEX);
    }

    public String getAdminStyle()
    {
        return getCSS(ADMIN_INDEX);
    }

    public String getContactStyle()
    {
        return getCSS(CONTACT_INDEX);
    }

    public String getPromotionsStyle()
    {
        return getCSS(PROMOTIONS_INDEX);
    }

    // methods for determining the current tab
    public boolean isStudentCurrent()
    {
        return index == STUDENT_INDEX;
    }

    public boolean isContactCurrent()
    {
        return index == CONTACT_INDEX;
    }

    public boolean isAdminCurrent()
    {
        return index == ADMIN_INDEX;
    }

    public boolean isNewsCurrent()
    {
        return index == NEWS_INDEX;
    }

    public boolean isPromotionCurrent()
    {
        return index == PROMOTIONS_INDEX;
    }
    public boolean isDatabaseCurrent()
    {
        return index == DATABASE_INDEX;
    }
    
    public boolean isPaymentCurrent()
    {
        return index == PAYMENT_INDEX;
    } 
    
    public boolean isCurrentCurret()
    {
        return secondIndex == FD_CURRENT_INDEX;
    }
    
    public boolean isAlumniCurrent()
    {
        return secondIndex == FD_ALUMNI_INDEX;
    }
    
    public boolean isCourseCurrent()
    {
        return secondIndex == FD_COURSE_INDEX;
    }
}
