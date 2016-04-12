package up;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Inspector {
	
    public static void main(String[] args) throws Exception {
        
    	int port = 8080;    	
    	/*
    	if (args.length > 0) 
    		port = Integer.parseInt(args[1]);    		    	
    	for (String op: args){
    		System.out.format("Run op: %s \n", op);
    	}
    	*/
    	port = Integer.parseInt(args[0]);
    	
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");            
        context.addServlet(new ServletHolder(new HelloServlet()),"/*");
        
        Server server = new Server(port);        
        server.setHandler(context);          
        server.start();
        server.join();
                
    }
}

