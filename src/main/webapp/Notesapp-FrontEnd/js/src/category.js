function myfunc(){
	window.location.href = "/view.jsp";
}

function create(){
	 document.getElementById("categorySubmit").onclick = updateCategory();
}

function addcategory(){
	var category = document.getElementById("category").value;
	if(category!="" && category!=null){
	ajaxcall("POST","/category","application/json","{categoryName:"+category+"}");
	}else{
		document.getElementById("error").innerHTML ="Mandatory field required";
	}
	
}

function updateCategory(id){
	var category = document.getElementById("category").value;
	ajaxcall("PUT","/category/"+id,"application/json","{categoryName:"+category+"}");
	
}

function ajaxcall(method,url,content_type,input_data){
	
	var xhr = new XMLHttpRequest();
	xhr.open(method, url, true);
	xhr.setRequestHeader('Content-Type', content_type);
	xhr.send(input_data);
	xhr.onreadystatechange = function() {
	   if (this.readyState == 4 && this.status == 200) {
		   console.log(this.responseText); 
	   }
	};
}