<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- Import the Struts2 tags library, allowing for Struts2 tags use within the JSP page -->
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Character set definition for the HTML document -->
    <meta charset="ISO-8859-1">
    
    <!-- The title that will appear on the browser tab -->
    <title>Login Form</title>
    
    <!-- Link to the external stylesheet which defines styles for elements on this page -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <!-- Center-aligned div to contain the login form -->
    <div align="center">
        <!-- Main header/title for the login form -->
        <h2>Login</h2>
        
        <!-- Struts2 form tag to create the login form. It posts the form data to the specified action URL -->
        <s:form action="loginAction" class="LoginForm">
            <!-- Text field for the user to input their username -->
            <s:textfield label="User Name" name="userName" class="formTextField" />
            
            <!-- Password field for the user to input their password. This ensures the typed content is hidden -->
            <s:password label="Password" name="password" class="formTextField" />
            
            <!-- Submit button to submit the form -->
            <s:submit value="Login" class="actionBtn" />
        </s:form>
    </div>

</body>
</html>
