$(function() {
	switch (menu) {
	case 'About':
		$('#about').addClass('active')
		break;
	case 'Contact':
		$('#contact').addClass('active')
		break;
	case 'Services':
		$('#services').addClass('active')
		break;
	default:
		$('#home').addClass('active')
		break;
	}

})
$(document).ready(function(){
	var $table=$('#productListTable');
	if($table.length){
		
		var jsonUrl='';
		if(window.categoryId==''){
			jsonUrl=window.contextRoot+'/json/data/all/products';
			console.log(jsonUrl);
		}else{
			jsonUrl=window.contextRoot+'/json/data/category/'+window.categoryId+'/products';
		}
		$('#productListTable').DataTable({
			lengthMenu:[[3,5,10,-1],['3 Record','5 Record','10 Record','All']],
			pageLength:3,
		ajax:{
			url:jsonUrl,
			dataSrc:  ''
			},
			columns: [
				{
					data:'code',
					mRender:function(data, type,row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="datatableImg"/>';
					}
				},
				{
					data:'name'
				},
				{
					data:'brand'
				},
				{ 
					data:'unitprice'
				},
				{
					data:'quantity'
				},
				{
					data:'id',
					bSortable:false,
					mRender: function(data, type, row) {
						var str='';
						str+='<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><sapn class="glyphyicon glyphicon-eye-open"></span>View</a> &#160;';
						str+='<a href="'+window.contextRoot+'/add/cart/'+data+'/product" class="btn btn-success"><sapn class="glyphyicon glyphicon-shopping-cart"></span>Cart</a>';
						return str;
					}
				}
			]
		});
	}
})