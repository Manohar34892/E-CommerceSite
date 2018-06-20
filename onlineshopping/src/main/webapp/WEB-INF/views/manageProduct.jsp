<div class="container">
	<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
	<div class="row">
		<c:if test="${not empty message }">
			<div class="col-xs-12">
				<div class="alert alert-success alert-dismiss">

					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>

		</c:if>

		<div class="col-md-offset-2 col-md-8 ">
			<div class="panel panel-primary">

				<div class="panel-heading">
					<hr>
					<h4 align="center">Product Management</h4>
					<hr>
				</div>
				<div class="panel-body">
					<sf:form class="form-horizontal " modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">
						<div class="form-group">

							<label class="control-label col-md-4" for="name">Enter
								Product Name:</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name"
									placeholder="Product Name" class="form-control" />
								<sf:errors path="name" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">

							<label class="control-label col-md-4" for="brand">Enter
								Brand Name:</label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand"
									placeholder="Brand Name" class="form-control" />
								<sf:errors path="brand" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Enter
								Description:</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description"
									placeholder="description" class="form-control" />
								<sf:errors path="description" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">

							<label class="control-label col-md-4" for="unitprice">Enter
								Price:</label>
							<div class="col-md-8">
								<sf:input type="number" path="unitprice" id="unitprice"
									placeholder="unitprice " class="form-control" />
								<sf:errors path="unitprice" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">

							<label class="control-label col-md-4" for="quantity">Enter
								Quantity:</label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity"
									placeholder="quantity " class="form-control" />

							</div>
						</div>
						<div class="form-group">

							<label class="control-label col-md-4" for="file">Select
								Image:</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="form-group">

							<label class="control-label col-md-4" for="categoryId">Enter
								Category :</label>
							<div class="col-md-8">
								<sf:select class="form-control" path="categoryId"
									id="categoryId" items="${categories}" itemLabel="name"
									itemValue="id" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-8" align="center" style="size: 100px;">
								<input type="submit" name="submit" id="submit" value="Submit"
									class="btn btn-primary">
								<sf:hidden path="id" />
								<sf:hidden path="code" />
								<sf:hidden path="supplierId" />
								<sf:hidden path="views" />
								<sf:hidden path="purchases" />
								<sf:hidden path="active" />
							</div>
						</div>
					</sf:form>
				</div>
			</div>

		</div>
	</div>
</div>