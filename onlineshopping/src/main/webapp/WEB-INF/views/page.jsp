

<!DOCTYPE html>
<html lang="en">

<head>
<!-- creating Spring tag lib -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<!-- Creating core tag lib -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"></c:set>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Vision Store-${title}</title>

<!-- Java Script -->
<script type="text/javascript">
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>
<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap theme-->
<link href="${css}/bootstrap-flatly-theme.css" rel="stylesheet">

<!-- Bootstrap Jquey  Data Table  -->
<link href="${css}/dataTables.bootstrap4.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">
		<!-- Navigation bar -->
		<%@include file="./shared/navbar.jsp"%>
		<!-- Page Content -->

		<!-- link the home page  -->

		<div class="content">
			<c:if test="${userClickHome==true}">
				<%@include file="home.jsp"%>
			</c:if>
		</div>
		<!-- link the about page  -->

		<c:if test="${userClickAbout==true}">
			<%@include file="about.jsp"%>
		</c:if>

		<!-- link the contact page  -->

		<c:if test="${userClickContact==true}">
			<%@include file="contact.jsp"%>
		</c:if>

		<!-- link the list product page  -->

		<c:if
			test="${userClickAllProducts==true or  userClickCategoryProducts==true}">
			<%@include file="listProduct.jsp"%>
		</c:if>

		<!-- link the show single product  page  -->

		<c:if test="${userClickShowProduct==true}">
			<%@include file="singleProduct.jsp"%>
		</c:if>

		<!-- link the manage product page  -->

		<c:if test="${userClickManageProduct==true}">
			<%@include file="manageProduct.jsp"%>
		</c:if>
		<!-- link the manage product page  -->

		<c:if test="${userClickCart==true}">
			<%@include file="cart.jsp"%>
		</c:if>

		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>

		<!-- Jquery -->
		<script src="${js}/jquery.js"></script>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/bootstrap.bundle.min.js"></script>


		<!-- Jquery -->
		<script src="${js}/jquery.dataTables.js"></script>
		
		<!-- Jquery BootBox js-->
		<script src="${js}/bootbox.min.js"></script>
		

		<!-- Jquery Datatable bootstrap-->
		<script src="${js}/dataTables.bootstrap4.js"></script>
		
		
		<!-- add Custom script -->
		<script src="${js}/myapp.js"></script>
	</div>
</body>

</html>
