<div class="container">
	<div class="row">
		<!-- Display the side bar -->


		<%@include file="./shared/sidebar.jsp"%>


		<!-- display the actual products -->

		<div class="col-lg-9">

			<div class="row">

				<div class="col-lg-15">

					<c:if test="${userClickAllProducts==true}">
						<script>
							window.categoryId = '';
						</script>
						<ul class="breadcrumb">
							<li><a href="${contextRoot}/home"> Home </a></li>
							<li class="active"> All Products</li>
						</ul>
					</c:if>

					<c:if test="${userClickCategoryProducts==true}">
						<script>
							window.categoryId = '${category.id}';
						</script>
						<ul class="breadcrumb">
							<li><a href="${contextRoot}/home"> Home </a></li>
							<li class="active"> category</li>
							<li class="active"> ${category.name}</li>
						</ul>
					</c:if>
				</div>
			</div>
			<div class="col-lg-12">
		<table id="productListTable"
			class="table table-striped table-borderd ">

			<thead>
				<tr>
					<th> </th> 
					<th>Name</th>
					<th>Brand</th>
					<th>Price</th>
					<th>Available Qty</th>
					<th> </th>
				</tr>

			</thead>
			<tfoot>
				<tr>
					<th> </th> 
					<th>Name</th>
					<th>Brand</th>
					<th>Price</th>
					<th>Available Qty</th>
					<th> </th> 
				</tr>
			</tfoot>
		</table>
		</div>
		
	</div>

	</div>
	

</div>