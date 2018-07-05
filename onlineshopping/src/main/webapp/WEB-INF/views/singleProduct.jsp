
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<div class="container">

	<div class="row">
		<div class="col-xs-12 ">
			<ul class="breadcrumb">
				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/show/all/products">Products</a></li>
				<li class="active">${product.name}</li>
			</ul>
		</div>

	</div>

	<div class="row">
		<div class="col-xs-12 col-sm-4">
			<!--Display the product Img  -->
			<div class="thumbnail">
				<img src="${images}/${product.code}.jpg" class="img img-responsive" />
			</div>
		</div>
		<!--Display the product Img  -->
		<div class="col-xs-15 col-sm-8">
			<h3>${product.name}</h3>
			<hr />
			<p>${product.description}</p>
			<hr />
			<h4>Price: &#8377; ${product.unitprice} /-</h4>
			<c:choose>

				<c:when test="${product.quantity <= 0}">
					<h6>
						Qty. Available : <span style="color: red">Out of Stock</span>
					</h6>
				</c:when>
				<c:otherwise>
					<h6>Qty. Available : ${product.quantity}</h6>
					<hr />
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${product.quantity <= 0}">
					<h6>
						<a href=" javascript:void(0)" class="btn btn-success disabled">
							<span class="glyphicon glyphicon-shopping-cart">Add to
								Cart</span>
						</a>
					</h6>
				</c:when>
				
				<c:otherwise>
					<a href=" ${contextRoot}/cart/add/${product.id}/product"
						class="btn btn-success"><span
						class="glyphicon glyphicon-shopping-cart">AddCart</span> </a>
				</c:otherwise>

			</c:choose>

			<security:authorize access="hasAuthority('ADMIN')">
				<a href=" ${contextRoot}/manage/${product.id}/product"
					class="btn btn-success"><span
					class="glyphicon glyphicon-shopping-cart">EDIT</span> </a>
			</security:authorize>
			<a href=" ${contextRoot}/show/all/products" class="btn btn-success"><span
				class="glyphicon glyphicon-shopping-cart">Back</span> </a>

		</div>
	</div>
</div>