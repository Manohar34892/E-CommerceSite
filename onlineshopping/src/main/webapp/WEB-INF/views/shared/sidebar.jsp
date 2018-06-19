
<div class="col-lg-3">

	<h5 class="my-3">Vison Shopping</h5>

	<div class="list-group">
		<c:forEach items="${categories}" var="category">
			<a href="${contextRoot}/show/category/${category.id}/products"
				class="list-group-item">${category.name}</a>
		</c:forEach>
	</div>

</div>
