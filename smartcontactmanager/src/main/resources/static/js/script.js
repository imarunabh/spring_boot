console.log("This is script file");

const toggleSidebar=()=>{
	if($(".sidebar").is(":visible")){
		
		
		
		$(".sidebar").css("display","none");
		$(".content").css("margin-left","0%")
	}
	
	else{
		$(".sidebar").css("display","block");
		$(".content").css("margin-left","20%")
	}
}

const search=()=>{
//	console.log("Searching...")
let query=$("#search-input").val();
console.log(query);

if(query==''){
	$(".search-result").hide();
}else{
	//search
	console.log(query);
	
	//sending request to server
	
	let url=`http://localhost:8080/search/${query}`
	
	fetch(url).then(response=>{
		return response.json();
	}).then(data=>{
		//data.......
		//console.log(data);
		
		let text=`<div class='list-group'>`;
		data.forEach((contact)=>{
			text+=`<a href="/user/${contact.cId}/contact" class='list-group-item list-group-item-action'>${contact.name} </a>`
		})
		
		
		
		text+=`</div>`;
		$(".search-result").html(text);
		$(".search-result").show();
		
		
	});
  }
 };
 
 
 //first request to server to create order
 
 const paymentStart=()=>{
	 console.log("Payment Started.....");
	 let amount=$("#payment_field").val();
	 console.log(amount);
	 if(amount=='' || amount==null){
		 alert("amount is required !!");
		 return ;
	 }
	 
	 $.ajax(
		 {
			 url:'/user/create_order',
			 data:JSON.stringify({amount:amount,info:'order_request'}),
			 contentType:'application/json',
			 type:'POST',
			 dataType:'json',
			 success:function(response){
				 //invoked where success
				 console.log(response)
				 
			 },
			 error:function(error){
				 //invoked when error
				 console.log(error)
				 alert("something went wrong !!")
			 }
		 }
	 )
	 
 }
 
 
 
 
 
 
 