function myfunc() {
	window.location.href = "/category.jsp";
}

function isName(event) {
	var charCode = (event.which) ? event.which : event.keyCode
	if (charCode > 64 && (charCode < 91 || charCode > 96 && charCode < 123)
			|| charCode == 8 || charCode == 46 || charCode == 32) {

		return true;
	}

	return false;
}

function addcategory() {
	var category = document.getElementById("category").value;
	if (category != "" && category != null) {
		document.getElementById("form").style.display = "none";
		categoryajaxcall("POST", "/category", "application/json", "{categoryName:"
				+ category + "}");
	} else {
		document.getElementById("error").innerHTML = "Mandatory field required";
	}

}

function edit(id,name){
	document.getElementById("category_list").innerHTML = `<input type="text" name="category" id="updatecategory" value="${name}" Placeholder="Category Name" onkeypress="return isName(event)" /><br />
														  <p id="error" class="red"></p>
														  <button id="categorySubmit" onclick="updateCategory(${id})" class="button">Save</button>`;
}

function remove(id){
	alert("Are you sure, want to Delete Catgeory");
	categoryajaxcall("DELETE", "/category/" + id, "application/json", "");
}
function updateCategory(id) {
	var category = document.getElementById("updatecategory").value;
	if (category != "" && category != null) {
		categoryajaxcall("PUT", "/category/" + id, "application/json", "{categoryName:" + category + "}");
	} else {
		document.getElementById("error").innerHTML = "Mandatory field required";
	}
}

function categoryNameCheck(name){
	if(name!="" && name!=null){
		return "Success";
	}else{
		return "Fail";
	}
}
function categoryTable(list) {
	var categoryObj = JSON.parse(list);
	var count=1;
	var table = '<h3 id="res-msg"></h3><h1>Category List</h1><label onclick="myfunc()" class="button green">Create</label><table id="tableCategory" class="category-table"><tr><th>S.No</th><th>Category Name</th><th>Created By</th><th>Created Date Time</th><th>Modified By</th><th>Modified Date Time</th><th>Action</th></tr>';
	for ( var i in categoryObj) {
		table += `<tr><td>${count}</td><td>${categoryObj[i].categoryName}</td><td>${categoryObj[i].createdBy}</td><td>${categoryObj[i].createdDatetime}</td><td>${categoryObj[i].modifiedBy}</td><td>${categoryObj[i].modifiedDatetime}</td><td><label onclick="edit(${categoryObj[i].id},'${categoryObj[i].categoryName}')" class="yellow_button">Edit</label> <label onclick="remove(${categoryObj[i].id})" class="red_button">Delete</label></td></tr>`;
		count++;
	}
	table+=`</table>`;
	document.getElementById("category_list").innerHTML = table;
}

function categoryajaxcall(method, url, content_type, input_data) {

	var xhr = new XMLHttpRequest();
	xhr.open(method, url, true);
	xhr.setRequestHeader('Content-Type', content_type);
	xhr.send(input_data);
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			categoryTable(this.responseText);
		}
	};
}
	