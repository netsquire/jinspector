package up;

import java.io.BufferedReader;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class HelloServlet extends HttpServlet {
    
	private static final long serialVersionUID = 7697874080056561605L;
	PrintStream out = System.out;
    
	public HelloServlet(){
		out.println(this.getClass().getName() + " started.");
		out.println("---------------------------------------------------- ");
	}
    
    String wrap(String s){
    	return ("<BR>" + s);
    }
    
    void doCommon (HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter web = response.getWriter();
        web.println(wrap("<h3> Results of request </h3>"));
        web.println(wrap("session=" + request.getSession(true).getId()));
        
        String method = request.getMethod();
    	out.println("METHOD: " + method);
    	web.println(wrap("METHOD: " + method));
    
    	String queryString = request.getQueryString();
    	if (queryString != null){
    		out.println("Query: " + queryString);
    		web.println(wrap("Query: " + queryString));
    		}
    	request.getPathInfo();
    	Enumeration<String> names = request.getHeaderNames();
        while(names.hasMoreElements()) {
        	String header = names.nextElement();
        	String headerValue = request.getHeader(header);
        	out.println(header + " -> " + headerValue);
        	web.println(wrap(header + " -> " + headerValue));
        	}
    	}
    
    void after() {
    	out.println("------------ \n");
        out.flush();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    														throws ServletException, IOException   {
    	doCommon(request, response);
        after();
    	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
															throws ServletException, IOException   {
    	doCommon(request, response);
    	PrintWriter web = response.getWriter();
    	String contentType = request.getContentType();
    	out.println("ContentType: " + contentType);
		if (contentType.contains("multiparts")) {
			Collection<Part> parts = request.getParts();
			out.println("Parts: ");
			for (Part part : parts) {
				out.println(" > " + part);
				web.println(wrap(" > " + part));
			}
		}
		//Gson gson = new Gson();
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

	    out.println(" JSON Body: " + jb.toString());
		//String jsonObject = gson.toJson(jb.toString());		  
		  	
    	Enumeration<String> pNames = request.getParameterNames();
    	if (pNames != null) {
    		//out.println("PARAMETERS: ");
    		while(pNames.hasMoreElements()){
    			out.println(" - " + pNames.nextElement());
    		}
    	}
    	after();
    }
    
    	
    
	}