package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicLong;

import javax.naming.NamingException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) throws IllegalArgumentException, NamingException, SQLException {
    	Application st = new Application();
    	
    	/** DataSource Implemetation  1 **/
    	Connection conn = st.jndiDataSource().getConnection();
    	Statement stmt = conn.createStatement();
    	int count = stmt.executeUpdate("update testtab set id=  2543571  where name= 'jagan' ");
    	System.out.println("RESULTED COUNT for FIRST CALL = " +  count);
    	
    	
    	/** DataSource Implemetation  2 **/
    	Connection conn1 = st.jndiDataSource1().getConnection();
    	Statement stmt1 = conn1.createStatement();
    	int count1 = stmt1.executeUpdate("update formtab set id=  5000  where name in ('atul','mani') ");
    	System.out.println("RESULTED COUNT for SECOND CALL = " +  count1);
    	
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}

