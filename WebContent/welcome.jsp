<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- Import the Struts2 tags library, allowing for Struts2 tags use within the JSP page -->
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Character set definition for the HTML document -->
    <meta charset="ISO-8859-1">
    
    <!-- The title that will appear on the browser tab -->
    <title>Welcome</title>
    
    <!-- Link to the external stylesheet which defines styles for elements on this page -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    
    <!-- Inline styles specifically for this page -->
    <style>
        /* Center text alignment for the header content */
        .centered-content {
            text-align: center;
        }
        
        /* Styles for the product table */
        .productTable {
            width: 750px;          /* Define a fixed width for the table */
            margin: auto;          /* Center the table horizontally */
        }
    </style>
</head>
<body>

    <!-- Header section -->
    <header class="centered-content">
        <!-- Main header/title of the page -->
        <h2>Welcome</h2>
        
        <!-- Link to navigate to the 'Add Product' page -->
        <a href="addProduct.jsp">
            <button class="actionBtn">Add New Product</button>
        </a>
    </header>
    
    <!-- Table displaying the list of products -->
    <table class="productTable">
        <thead>
            <tr>
                <!-- Table headers/column titles -->
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Product Category</th>
                <th>Product price</th>
                <!-- Combined header for Update and Delete columns (occupies two column spaces due to colspan attribute) -->
                <th colspan="2">Actions</th>
            </tr>
        </thead>
        
        <tbody>
            <!-- Iterate through the 'products' list, storing each product as 'product' -->
            <s:iterator value="products" var="product">
                <tr>
                    <!-- Display various properties of each product -->
                    <td><s:property value="#product.productId"/>		</td>
                    <td><s:property value="#product.productName"/>		</td>
                    <td><s:property value="#product.productCategory"/>	</td>
                    <td><s:property value="#product.productPrice"/>		</td>
                    
                    <!-- Use the set tag to store productId in a variable -->
        			<s:set var="tempProductId" value="#product.productId" />
                    <!-- Placeholder cells for Update and Delete actions. Actual actions can be implemented here. -->
                    <td>
                   		<!-- Use the stored productId for constructing the link -->
                    	<a href="<s:url action='updateDataAction'><s:param name='product.productId' value='#tempProductId'/></s:url>">
                    		<button class="actionBtn">Update</button>
                    	</a>
                    </td>
                    <td>
                    	<a href="<s:url action='deleteAction'><s:param name='product.productId' value='#tempProductId'/></s:url>">
                    		<button class="actionBtn">Delete</button>
                    	</a>
                    </td>
                </tr>
            </s:iterator>
        </tbody>
    </table>

</body>
</html>
