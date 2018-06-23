$(function() {
	switch (menu) {
	case 'About':
		$('#about').addClass('active')
		break;
	case 'Contact':
		$('#contact').addClass('active')
		break;
	case 'Products':
		$('#listProducts').addClass('active')
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active')
		break;
	default:
		if(menu='home')break;
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
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
						str+='<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphyicon glyphicon-eye-open"></span>View</a> &#160;';
						str+='<a href="'+window.contextRoot+'/add/cart/'+data+'/product" class="btn btn-success"><span class="glyphyicon glyphicon-shopping-cart"></span>Cart</a>';
						return str;
					}
				}
			]
		});
	}
	
	var $alert=$('.alert')
	if($alert.length){
		setTimeout(() => {
			$alert.fadeOut('slow');
		}, 1000);
	}
	
	// ----------------Chaeck Box
	
	$('.switch input[type="checkbox"]').on('change',function(){
		var checkbox=$(this);
		var checked=checkbox.prop('checked');
		var dMsg=(checked)? 'You Want to active The Product?':
							'You Want to Deactive The Product?';
		var value=checkbox.prop('value');
		bootbox.confirm({
			size:'medium',
		    title:"Product Activation & Deactivation?",
		    message:dMsg ,
		      
		   
		    callback: function (confirmed) {
		        if(confirmed){
		    	console.log(value);
		    	bootbox.alert({
		    		size:'medium',
		    				title: "Information",
		    				message:'You are going to Perform operation' ,
		    		    	})
		        }else{
		        	checkbox.prop('checked',!checked);
		        }
		    }
		});
	})
})