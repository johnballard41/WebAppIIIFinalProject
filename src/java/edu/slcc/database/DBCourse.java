package edu.slcc.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named(value = "dbc")
@ApplicationScoped
public class DBCourse implements Serializable {

    String crn = "";
    String title = "";
    String semester = "";
    String year = "";

    String result = "";

    public DBCourse() {
        DatabaseConnection.connection();
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void clear() {
        title = "";
        semester = "";
        year = "";
        crn = "";

        result = "";
    }
    
        public String getConnectionResponse() {
        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return null;
        }
        if (con != null) {
            return "<p style=\"color:green\">Connection succesfull! <br />";
        } else {
            DatabaseConnection.connection();
            return "<p style=\"color:red\">Connection failed! <br />";
        }
    }

    public void listAll() {
        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        String table = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlStr = "SELECT  *  FROM course";
        try {
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            //execute
            rs = ps.executeQuery();
            while (rs.next()) {
                String crn = rs.getString(1) + " ";
                String title = rs.getString(2) + " ";
                String semester = rs.getString(3) + " ";
                String year = rs.getInt(4) + " ";
                table += crn + title + semester + year + "</br>";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                DatabaseConnection.closeDatabaseConnection(con);
                // close the resources 
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        result = table;
    }

    public void viewCourse() {
        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        String ret = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlStr = "SELECT *  FROM course WHERE snumber=?";
        try {
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            ps.setString(1, crn);
            //execute
            rs = ps.executeQuery();
            if (rs.next()) {
                this.crn = rs.getString("crn");
                ret += this.crn + " ";
                this.title = rs.getString("title");
                ret += this.title + " ";
                this.semester = rs.getString("semester");
                ret += this.semester + " ";
                this.year = rs.getString("year");
                ret += this.year + " ";             
            } else {
                ret = this.crn + " doesn't exist.";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                DatabaseConnection.closeDatabaseConnection(con);
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        this.result = ret;
    }

    public void updateCourse() {
        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement updateSupplier = null;
        try {

            updateSupplier = con.prepareStatement(
                    "UPDATE course SET crn=?, title=?, semester=?, year=? WHERE crn=?");

            updateSupplier.setString(1, crn);
            updateSupplier.setString(2, title);
            updateSupplier.setString(3, semester);
            updateSupplier.setString(4, year);
            int updateCount = updateSupplier.executeUpdate();

            result = "number of rows affected: " + updateCount;

        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            try {
                DatabaseConnection.closeDatabaseConnection(con);
                // close the resources 
                if (updateSupplier != null) {
                    updateSupplier.close();
                }

            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
    }

    public void deleteCourse() {

        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement ps = null;
        int rowsAffected = -1;

        try {
            String query = "DELETE FROM course WHERE crn=? ";
            ps = con.prepareStatement(query);
            ps.setString(1, crn);
            rowsAffected = ps.executeUpdate();
            result = "number of rows affected: " + rowsAffected;
        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            try {
                DatabaseConnection.closeDatabaseConnection(con);
                // close the resources 
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
    }

    public void insertCourse() {
        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement updateCourse = null;
        try {
            updateCourse = con.prepareStatement(
                    "INSERT INTO course (crn, title, semester, year) "
                    + "VALUES ( ?, ?, ? , ?)");
            updateCourse.setString(1, crn);
            updateCourse.setString(2, title);
            updateCourse.setString(3, semester);
            updateCourse.setString(4, year);

            int updateCount = updateCourse.executeUpdate();

            result = "number of rows affected: " + updateCount;
        } catch (Exception ex) {
            System.err.println(ex.toString());
            result = ex.toString();
        } finally {
            try {
                DatabaseConnection.closeDatabaseConnection(con);
                // close the resources 
                if (updateCourse != null) {
                    updateCourse.close();
                }

            } catch (SQLException e) {
                System.err.println(e.toString());
                result = e.toString();
            }
        }
    }

    public String getResult() {
        return "<p style=\"color:green\">Course <br />" + result;

    }
}
