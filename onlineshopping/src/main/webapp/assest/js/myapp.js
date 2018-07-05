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
		if(menu=='home')break;
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
						
						if(userRole=='ADMIN'){
							str+='<a href='+window.contextRoot+'/manage/products/'+data+'/edit  class="btn btn-warring"><span class="glyphicon glyphicon-pencil"></span>Edit</a>';
						
						}else{
						
						if(row.quantity < 1){
							str=+'<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphyicon glyphicon-shopping-cart"></span>Cart</a>';
						}else{
							
								str+='<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphyicon glyphicon-shopping-cart"></span>Cart</a>';
							}
						}
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
	
	
})
// ---------------------admin table

$(document).ready(function(){
	var $table=$('#adminProductTable');
	if($table.length){
		
		var jsonUrl=window.contextRoot+'/json/data/admin/all/products';
		
		$('#adminProductTable').DataTable({
			lengthMenu:[[10,30,50,-1],['10 Record','30 Record','50 Record','All']],
			pageLength:10,
		ajax:{
			url:jsonUrl,
			dataSrc:  ''
			},
			columns: [
				{
					data:'id'
				},
				{
					data:'code',
					mRender:function(data, type,row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adninDataTableImg"/>';
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
					data:'active',
					bSortable:false,
					mRender: function(data, type, row) {
						var str='';
				// toogle switch
						str+='<label class="switch">'
							if(data){
						str+='<input type="checkbox" checked="checked" value="'+row.id+'">'
							}else{
								str+='<input type="checkbox"  value="'+row.id+'">'
							}
						str+='	<span class="slider round"></span>	</label>'
						return str;
					}
				},
				{
					data:'id',
					bSortable:false,
					mRender: function(data, type, row) {
						var str='';
						str+='<a href='+window.contextRoot+'/manage/products/'+data+'/edit  class="btn btn-warring">';
						str+=	'<span class="glyphicon glypnicon-pencil"></span>Edit</a>';
						return str;
					}
				}
			],
			initComplete:function(){
				var api=this.api();
				api.$('.switch input[type="checkbox"]').on('change',function(){
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
					    	var activationUrl=window.contextRoot+"/manage/products/"+value+"/activation";
					    	$.post(activationUrl,function(data){
					    		bootbox.alert({
						    		size:'medium',
						    				title: "Information",
						    				message:'You are going to Perform operation' ,
						    		    	})
						        
					    	})
					        }
					    	else{
					        	checkbox.prop('checked',!checked);
					        }
					    }
					});
				})
			}
		});
	}
	
	
	$('button[name="refreshCart"]').click(function(){
		var cartLineID =$(this).attr('value');
		var cartElement=$('#count_'+cartLineID)
		var currentCuont=cartElement.val()
		
		
		var  updateUrl= window.contextRoot+"/cart/"+cartLineID+"/update?count="+currentCuont;
		window.location.href=updateUrl;
	}); 


	
});
/* handling click event  refreshcart services*/

