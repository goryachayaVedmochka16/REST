package org.jcs.service;

import java.util.ArrayList;
import java.sql.*;

import org.jcs.bean.Country;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.*;

/*
 * It is just a helper class which should be replaced by database implementation.
 * It is not very well written class, it is just used for demonstration.
 */
public class CountryService {
	
	private static final String url = "jdbc:mysql://localhost:3306/countries";
    private static final String user = "root";
    private static final String password = "admin";
    
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


 public CountryService() {
  super();
 }
 
 public ArrayList<Country> getAllCountries()
 {
     String query = "select count(*) from ctable";
     ArrayList<Country> ans = new ArrayList<Country>();

     try {
         con = DriverManager.getConnection(url, user, password);
         stmt = con.createStatement();
         rs = stmt.executeQuery(query);
         while (rs.next()) { ans.add(new Country(rs.getInt("id"), rs.getString("countryname"), rs.getLong("population"))); }
     } catch (SQLException sqlEx) {
     }  finally {return ans;}
 }
 
 public Country getCountry(int id)
 {
     String query = "select * from ctable where id = " + id;
     Country ans = new Country();

     try {
         con = DriverManager.getConnection(url, user, password);
         stmt = con.createStatement();
         rs = stmt.executeQuery(query);
         ans = new Country(rs.getInt("id"), rs.getString("countryname"), rs.getLong("population")); 
     } catch (SQLException sqlEx) {
     }  finally { return ans; }
 }
 
public Country addCountry(Country country)
{
    String query = "insert into ctable (countryname, population) values ('" + country.getCountryName() + "', '" + country.getPopulation() + "'" ;
    try {
        con = DriverManager.getConnection(url, user, password);
        stmt = con.createStatement();
        stmt.executeQuery(query);
    } catch (SQLException sqlEx) {
    } finally {return country;}
} 

 public Country updateCountry(Country country) {
	 if(country.getId()<=0) return null;
	 String query = "update ctable set countryname = '" + country.getCountryName() + "', population = " + country.getPopulation() + "where id = " + country.getId() ;
	 try {
	        con = DriverManager.getConnection(url, user, password);
	        stmt = con.createStatement();
	        stmt.executeQuery(query);
	    } catch (SQLException sqlEx) {
	    } finally {return country;}
	} 

 public void deleteCountry(int id){
	 if(id<=0) return;
	 String query = "delete from ctable where id = " + id;
	 try {
	        con = DriverManager.getConnection(url, user, password);
	        stmt = con.createStatement();
	        stmt.executeQuery(query);
	    } catch (SQLException sqlEx) {}
	}
 
 private static int getMaxId()
 {
     String query = "select max(id) as mx from ctable";
     int ans = -1;

     try {
         con = DriverManager.getConnection(url, user, password);
         stmt = con.createStatement();
         rs = stmt.executeQuery(query);
         ans = rs.getInt("mx");
     } catch (SQLException sqlEx) {
     }  finally {return ans;}
 }

 public Long getPopTotal()
 {
     String query = "select sum(id) as tt from ctable";
     long ans = -1;
     try {
         con = DriverManager.getConnection(url, user, password);
         stmt = con.createStatement();
         rs = stmt.executeQuery(query);
         ans = rs.getLong("tt");
     } catch (SQLException sqlEx) {
     }  finally {return ans;}
 }

 public Float getPopAvg()
 {
     String query = "select sum(id) as av from ctable";
     float ans = -1;
     try {
         con = DriverManager.getConnection(url, user, password);
         stmt = con.createStatement();
         rs = stmt.executeQuery(query);
         ans = rs.getFloat("av");
     } catch (SQLException sqlEx) {
     }  finally {return ans;}
 }
 
public ArrayList<String> getMethods() {
	ArrayList<String> ans = new ArrayList<String>(); 
	
	Class countryServiceClass = CountryService.class; 
	//Get the methods
	Method[] methods = countryServiceClass.getDeclaredMethods(); 
	//Loop through the methods and print out their names
	for (Method method : methods)
	{
		int mods = method.getModifiers();
		if (Modifier.isPublic(mods)) 
			ans.add(method.getName());
	}
	
	return ans;
}


}