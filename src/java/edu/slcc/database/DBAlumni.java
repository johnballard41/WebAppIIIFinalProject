package edu.slcc.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named(value = "dba")
@ApplicationScoped
public class DBAlumni implements Serializable {

    String lolaID = "";
    String firstN = "";
    String middleN = "";
    String lastN = "";
    String s_email = "";
    String street = "";
    String city = "";
    String zip = "";
    String state = "";
    String phone = "";
    String achieve = "";
    String em_name = "";
    String em_add = "";
    String em_phone = "";
    String em_email = "";

    String result = "";

    public DBAlumni() {
        DatabaseConnection.connection();
    }

    public String getLolaID() {
        return lolaID;
    }

    public void setLolaID(String lolaID) {
        this.lolaID = lolaID;
    }

    public String getFirstN() {
        return firstN;
    }

    public void setFirstN(String firstN) {
        this.firstN = firstN;
    }

    public String getMiddleN() {
        return middleN;
    }

    public void setMiddleN(String middleN) {
        this.middleN = middleN;
    }

    public String getLastN() {
        return lastN;
    }

    public void setLastN(String lastN) {
        this.lastN = lastN;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAchieve() {
        return achieve;
    }

    public void setAchieve(String achieve) {
        this.achieve = achieve;
    }

    public String getS_email() {
        return s_email;
    }

    public void setS_email(String s_email) {
        this.s_email = s_email;
    }

    public String getEm_name() {
        return em_name;
    }

    public void setEm_name(String em_name) {
        this.em_name = em_name;
    }

    public String getEm_add() {
        return em_add;
    }

    public void setEm_add(String em_add) {
        this.em_add = em_add;
    }

    public String getEm_phone() {
        return em_phone;
    }

    public void setEm_phone(String em_phone) {
        this.em_phone = em_phone;
    }

    public String getEm_email() {
        return em_email;
    }

    public void setEm_email(String em_email) {
        this.em_email = em_email;
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

    public void clear() {
        lolaID = "";
        firstN = "";
        middleN = "";
        lastN = "";
        street = "";
        city = "";
        zip = "";
        state = "";
        phone = "";
        achieve = "";
        s_email = "";
        em_name = "";
        em_add = "";
        em_phone = "";
        em_email = "";

        result = "";
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
        String sqlStr = "SELECT  *  FROM alumni";
        try {
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            //execute
            rs = ps.executeQuery();
            while (rs.next()) {
                String lolaID = rs.getString(1) + " ";
                String firstN = rs.getString(2) + " ";
                String middleN = rs.getString(3) + " ";
                String lastN = rs.getString(4) + " ";
                String s_email = rs.getString(5) + " ";
                String street = rs.getString(6) + " ";
                String city = rs.getString(7) + " ";
                String zip = rs.getString(8) + " ";
                String state = rs.getString(9) + " ";
                String phone = rs.getString(10) + " ";
                String achieve = rs.getString(11) + " ";
                String em_name = rs.getString(12) + " ";
                String em_add = rs.getString(13) + " ";
                String em_phone = rs.getString(14) + " ";
                String em_email = rs.getString(15) + " ";
                table += lolaID + firstN + middleN + lastN + s_email + street + city + zip
                        + state + phone + achieve + em_name + em_add + em_phone + em_email +"</br>";
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

    public void viewCurrent() {
        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        String ret = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlStr = "SELECT *  FROM alumni WHERE lola_id=?";
        try {
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            ps.setString(1, lolaID);
            //execute
            rs = ps.executeQuery();
            if (rs.next()) {
                this.lolaID = rs.getString("lola_id");
                ret += this.lolaID + " ";
                this.firstN = rs.getString("first");
                ret += this.firstN + " ";
                this.middleN = rs.getString("middle");
                ret += this.middleN + " ";
                this.lastN = rs.getString("last");
                ret += this.lastN + " ";
                this.s_email = rs.getString("email");
                ret += this.s_email + " ";
                this.street = rs.getString("street");
                ret += this.street + " ";
                this.city = rs.getString("city");
                ret += this.city + " ";
                this.zip = rs.getString("zip");
                ret += this.zip + " ";
                this.state = rs.getString("state");
                ret += this.state + " ";
                this.phone = rs.getString("phone");
                ret += this.phone + " ";
                this.achieve = rs.getString("achievements");
                ret += this.achieve + " ";
                this.em_name = rs.getString("employer");
                ret += this.em_name + " ";
                this.em_add = rs.getString("address");
                ret += this.em_add + " ";
                this.em_phone = rs.getString("employer_phone");
                ret += this.em_phone + " ";
                this.em_email = rs.getString("employer_email");
                ret += this.em_email + " ";
            } else {
                ret = this.lolaID + " doesn't exist.";
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

    public void updateCurrent() {
        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement updateSupplier = null;
        try {
            updateSupplier = con.prepareStatement(
                    "UPDATE alumni SET lola_id=?, first=?, middle=?, last=?, email=?,"
                    + " street=?, city=?, zip=?, state=?, phone=?, achievements=?, employer=?,"
                            + " address=?, employer_phone=?, employer_email=? WHERE lola_id=?");
            updateSupplier.setString(1, lolaID);
            updateSupplier.setString(2, firstN);
            updateSupplier.setString(3, middleN);
            updateSupplier.setString(4, lastN);
            updateSupplier.setString(5, s_email);
            updateSupplier.setString(6, street);
            updateSupplier.setString(7, city);
            updateSupplier.setString(8, zip);
            updateSupplier.setString(9, state);
            updateSupplier.setString(10, phone);
            updateSupplier.setString(11, achieve);
            updateSupplier.setString(12, em_name);
            updateSupplier.setString(13, em_add);
            updateSupplier.setString(14, em_phone);
            updateSupplier.setString(15, em_email);
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

    public void deleteCurrent() {

        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement ps = null;
        int rowsAffected = -1;

        try {
            String query = "DELETE FROM alumni WHERE lola_id=? ";
            ps = con.prepareStatement(query);
            ps.setString(1, lolaID);
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

    public void insertCurrent() {
        Connection con = DatabaseConnection.connection();
        if (con == null) {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement updateSupplier = null;
        try {
            updateSupplier = con.prepareStatement(
                    "INSERT INTO alumni (lola_id, first, middle, last, email, "
                    + "street, city, zip, state, phone, achievements, employer, address, employer_phone, employer_email) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            updateSupplier.setString(1, lolaID);
            updateSupplier.setString(2, firstN);
            updateSupplier.setString(3, middleN);
            updateSupplier.setString(4, lastN);
            updateSupplier.setString(5, s_email);
            updateSupplier.setString(6, street);
            updateSupplier.setString(7, city);
            updateSupplier.setString(8, zip);
            updateSupplier.setString(9, state);
            updateSupplier.setString(10, phone);
            updateSupplier.setString(11, achieve);
            updateSupplier.setString(12, em_name);
            updateSupplier.setString(13, em_add);
            updateSupplier.setString(14, em_phone);
            updateSupplier.setString(15, em_email);

            int updateCount = updateSupplier.executeUpdate();

            result = "number of rows affected: " + updateCount;
        } catch (Exception ex) {
            System.err.println(ex.toString());
            result = ex.toString();
        } finally {
            try {
                DatabaseConnection.closeDatabaseConnection(con);
                // close the resources 
                if (updateSupplier != null) {
                    updateSupplier.close();
                }

            } catch (SQLException e) {
                System.err.println(e.toString());
                result = e.toString();
            }
        }
    }

    public String getResult() {
        return "<p style=\"color:green\">Alumni <br />" + result;

    }
}
