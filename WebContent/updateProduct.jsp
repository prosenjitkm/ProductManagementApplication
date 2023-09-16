<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Update Product</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<div align="center">
		<h2>Update Product</h2>
		
		<!-- A Struts2 form that posts the data to the addAction in the specified namespace -->
		<s:form action="updateAction" class="formTable">
			
			<!-- Input field for Product ID -->
			<s:textfield label="Product ID" name="product.productId" class="formTextField" readonly = "true" />
			
			<!-- Input field for Product Name -->
			<s:textfield label="Product Name" name="product.productName" class="formTextField" />
			
			<!-- Input field for Product Category (Typo fixed: Catagory to Category) -->
			<s:textfield label="Product Category" name="product.productCategory" class="formTextField" />
			
			<!-- Input field for Product Price -->
			<s:textfield label="Product Price" name="product.productPrice" class="formTextField" />
			
			<!-- Submit button for the form -->
			<s:submit value="Update Product" class="actionBtn" />
			
		</s:form>
	</div>

</body>
</html>