<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Servlet Info</title>
    </head>
    <body>
        <div>
           <% 
            String message = (String) request.getAttribute("servletValue");
            out.println(message);
             %>
        </div>
        
    </body>
</html>