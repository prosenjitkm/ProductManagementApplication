<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- Import the Struts2 tags library, which allows using Struts2 tags within the JSP page -->
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
	<!-- Character set definition for the HTML document -->
	<meta charset="ISO-8859-1">
	
	<!-- The title that will appear on the browser tab -->
	<title>Add New Product</title>
	
	<!-- Link to the stylesheet which defines styles for elements on this page -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<!-- A centered div containing the form for adding a new product -->
	<div align="center">
		<h2>Add New Product</h2>
		
		<!-- A Struts2 form that posts the data to the addAction in the specified namespace -->
		<s:form action="addAction" class="formTable">
			
			<!-- Input field for Product ID -->
			<s:textfield label="Product ID" name="product.productId" class="formTextField" />
			
			<!-- Input field for Product Name -->
			<s:textfield label="Product Name" name="product.productName" class="formTextField" />
			
			<!-- Input field for Product Category (Typo fixed: Catagory to Category) -->
			<s:textfield label="Product Category" name="product.productCategory" class="formTextField" />
			
			<!-- Input field for Product Price -->
			<s:textfield label="Product Price" name="product.productPrice" class="formTextField" />
			
			<!-- Submit button for the form -->
			<s:submit value="Add Product" class="actionBtn" />
			
		</s:form>
	</div>

</body>
</html>
