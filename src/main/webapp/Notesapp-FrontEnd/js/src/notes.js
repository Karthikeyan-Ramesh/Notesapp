function notesbox(){
	var id = document.getElementById("categoryId").value;
	if(id === 'other'){
		document.getElementById("notesbox").innerHTML = `<div id="form noteform" ><input type="text" name="category" id="category" Placeholder="Category Name" onkeypress="return isName(event)" /><br /><p id="error" class="red"></p><button id="categorySubmit" onclick="addcategory()" class="button">Save</button></div>`;
	}else if(id!=0) {
		noteajaxcall("GET", "/note/"+id, "application/json", "",1);
	}else{
		document.getElementById("notesbox").innerHTML = "";
	}
}

function addcategory() {
	var category = document.getElementById("category").value;
	if (category != "" && category != null) {
		noteajaxcall("POST", "/category", "application/json", "{categoryName:"
				+ category + "}",2);
		document.getElementById("notesbox").innerHTML ="";
		categoryListOnload();
	} else {
		document.getElementById("error").innerHTML = "Mandatory field required";
	}

}

function isName(event) {
	var charCode = (event.which) ? event.which : event.keyCode
	if (charCode > 64 && (charCode < 91 || charCode > 96 && charCode < 123)
			|| charCode == 8 || charCode == 46 || charCode == 32) {

		return true;
	}

	return false;
}

function categoryListOnload(){
	noteajaxcall("GET", "/category", "application/json", "",2);
}

function categoryDropdown(list){
	let categoryObj = JSON.parse(list);
	if(categoryObj.hasOwnProperty('listSize')) { 
		categoryObj = null;
	}
	if(categoryObj!=null){
		/*let noteObj = noteajaxcall("GET", "/note/5101733952880640", "application/json", "",3);
		console.log(noteObj);
		if(noteObj.hasOwnProperty('listSize')) { 
			noteObj = null;
		}*/
		let dropdown = `<select id="categoryId" name="categoryId" class="categorydropdown shadow" onchange="notesbox()"><option value="0">-- Select Category --</option>`;
		for (i in categoryObj) {
			if(i==1)
				dropdown+= `<option value="${categoryObj[i].id}" selected>${categoryObj[i].categoryName}</option>`; 
			else
				dropdown+= `<option value="${categoryObj[i].id}">${categoryObj[i].categoryName}</option>`;
		}
		document.getElementById("categoryList").innerHTML = dropdown+`<option value="other">Other</option></select><br/>`;
		notesbox();
	}
	else{
		document.getElementById("notesbox").innerHTML = `<div id="form noteform" ><input type="text" name="category" id="category" Placeholder="Category Name" onkeypress="return isName(event)" /><br /><p id="error" class="red"></p><button id="categorySubmit" onclick="addcategory()" class="button">Save</button></div>`;
	}
}


function notesList(list){
	console.log(list);
	var noteObj = JSON.parse(list);
	if(noteObj.hasOwnProperty('listSize')) { 
			noteObj = null;
	}
	if(noteObj!=null && noteObj!=''){
		var i = 0;
		var notes="";
		var count =1;
		for (i in noteObj) {
			if(i==0){
				notes+=`<input type="hidden" id="identity${count}" value="${noteObj[i].id}" /><h4 id="when">${noteObj[i].createdDatetime}</h4><input type="text" class="nname" value="${noteObj[i].noteName}" id="noteName1" Placeholder="Enter Your Note Name" /><span><a href='#' class="add" onclick="add()">+</a><a href='#' id="minus" class="min  hidden" onclick="remove()">-</a></span><br/><textarea class="notesContent" id="notesContent1" rows="9" cols="60" placeholder="Enter the Notes" >${noteObj[i].noteDescription}</textarea><div id='multibutton'><label onclick="savenote(1)" class="button greennote shadow spacing lalign">Save</label><label onclick="deletenote(${noteObj[i].id})" class="button rednote shadow spacing ">Delete</label></div>`;
			}else{
				notes+=`<input type="hidden" id="identity${count}" value="${noteObj[i].id}" /><h4 id="when">${noteObj[i].createdDatetime}</h4><input type="text" class="nname noteadjust" value="${noteObj[i].noteName}" id="noteName${count}" Placeholder="Enter Your Note Name" /><br/><textarea class="notesContent" id="notesContent${count}" rows="9" cols="60" placeholder="Enter the Notes" >${noteObj[i].noteDescription}</textarea><div id='multibutton'><label onclick="savenote(${count})" class="button greennote shadow spacing lalign">Save</label><label onclick="deletenote(${noteObj[i].id})" class="button rednote shadow spacing ">Delete</label></div>`;
			}
			count++;
		}
		count--;
		document.getElementById("notesbox").innerHTML = `<input type="hidden" id="rowcount" value="${count}"/>`+notes;
	}else{
		document.getElementById("notesbox").innerHTML = `<input type="hidden" id="rowcount" value="1"/><input type="hidden" id="identity1" value="0" /><input type="text" class="nname" value="Note1" id="noteName1" Placeholder="Enter Your Note Name" /><span><a href='#' class="add" onclick="add()">+</a><a href='#' id="minus" class="min  hidden" onclick="remove()">-</a></span><br/><textarea class="notesContent" id="notesContent1" rows="9" cols="60" placeholder="Enter the Notes" ></textarea><div id='multibutton'><label onclick="savenote(1)" class="button greennote shadow spacing">Save</label></div>`;
	}
	
}

function add(){
	 var count = document.getElementById("rowcount").value;
	 var i = parseInt(count)+1;
	 if(i<=6){
	 var newdiv = document.createElement('div');
	 newdiv.innerHTML = `<input type="hidden" id="identity${i}" value="0" /><input type="text" class="nname noteadjust" value="Note${i}" id="noteName${i}" Placeholder="Enter Your Note Name" /><br/><textarea class="notesContent" id="notesContent${i}" rows="9" cols="60" placeholder="Enter the Notes" ></textarea><div id='multibutton'><label onclick="savenote(${i})" class="button greennote shadow spacing">Save</label></div>`;
	 document.getElementById("notesbox").appendChild(newdiv);
	 document.getElementById("rowcount").value = i;
	 }
}

function remove(){
	 var i = document.getElementById("rowcount").value;
	 document.getElementById("notesbox").removeChild();
	 document.getElementById("rowcount").value = parseInt(i)-1;
}

function savenote(count){
	var noteName = document.getElementById("noteName"+count).value;
	var notesContent = document.getElementById("notesContent"+count).value;
	var categoryId = document.getElementById("categoryId").value;
	var identity = document.getElementById("identity"+count).value;
	if(noteName=="" && noteName==null){
		noteName = "Note"+count;
	}
	/*
	 * if(notesContent!="" && notesContent!=null){ let arr =
	 * notesContent.split("\n"); for(let i=0;i<arr.length;i++){
	 * notesContent+=(i == arr.length-1)?arr[i]:arr[i]+"~`"; } }
	 */
	
	if(identity == 0){
		noteajaxcall("POST", "/note", "application/json", "{noteName:"+ noteName + ",noteDescription:"+notesContent+",categoryId:"+categoryId+"}",1);
		setTimeout(function(){ alert("Note was created successfully !"); }, 1000);
	}else{
		noteajaxcall("PUT", "/note/"+identity, "application/json", "{noteName:"+ noteName + ",noteDescription:"+notesContent+",categoryId:"+categoryId+"}",1);
		setTimeout(function(){ alert("Note was updated successfully !"); }, 1000);
	}
	
}

function deletenote(id){
	noteajaxcall("DELETE", "/note/"+id, "application/json", "",1);
	setTimeout(function(){ alert("Note was deleted successfully !"); }, 1000);
}

function noteajaxcall(method, url, content_type, input_data,flag) {

	var xhr = new XMLHttpRequest();
	xhr.open(method, url, true);
	xhr.setRequestHeader('Content-Type', content_type);
	xhr.send(input_data);
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if(flag==1)
				notesList(this.responseText);
			else if(flag==2)
				categoryDropdown(this.responseText);
			else{
				let result = this.responseText;
				return result;
			}
		}
	};
}