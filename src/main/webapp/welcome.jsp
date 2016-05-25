<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome</title>
    </head>

    <body>
        <%
            String userName = (String) session.getAttribute("Username");
            String additionalURL = (String) request.getAttribute("alias");
            if (userName != null) {
                out.write("Hello" + userName);
           } else {
                out.write("Hello World!");
            }
            if (additionalURL == null) {
                additionalURL = "";
            }
        %>

        <form method="post" action="<%=request.getContextPath()%>/customServlet">
            <input type="submit" value="View My Name"/>
            <input type="hidden" name="_s" value="<%=additionalURL%>"/>
        </form>
    </body>
</html>