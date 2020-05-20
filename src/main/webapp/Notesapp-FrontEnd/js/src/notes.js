function notesbox(){
	var id = document.getElementById("categoryId").value;
	if(id!=0) {
		noteajaxcall("GET", "/note/"+id, "application/json", "");
	}else{
		document.getElementById("notesbox").innerHTML = "";
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
				notes+=`<input type="hidden" id="identity${count}" value="${noteObj[i].id}" /><input type="text" class="nname" value="${noteObj[i].noteName}" id="noteName1" Placeholder="Enter Your Note Name" /><span><a href='#' class="add" onclick="add()">+</a><a href='#' id="minus" class="min  hidden" onclick="remove()">-</a></span><br/><textarea class="notesContent" id="notesContent1" rows="9" cols="60" placeholder="Enter the Notes" >${noteObj[i].noteDescription}</textarea><div id='multibutton'><label onclick="savenote(1)" class="button greennote shadow spacing lalign">Save</label><label onclick="deletenote(${noteObj[i].id})" class="button rednote shadow spacing ">Delete</label></div>`;
			}else{
				notes+=`<input type="hidden" id="identity${count}" value="${noteObj[i].id}" /><input type="text" class="nname noteadjust" value="${noteObj[i].noteName}" id="noteName${count}" Placeholder="Enter Your Note Name" /><br/><textarea class="notesContent" id="notesContent${count}" rows="9" cols="60" placeholder="Enter the Notes" >${noteObj[i].noteDescription}</textarea><div id='multibutton'><label onclick="savenote(${count})" class="button greennote shadow spacing lalign">Save</label><label onclick="deletenote(${noteObj[i].id})" class="button rednote shadow spacing ">Delete</label></div>`;
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
	/*if(notesContent!="" && notesContent!=null){
		let arr = notesContent.split("\n");
		for(let i=0;i<arr.length;i++){
			notesContent+=(i == arr.length-1)?arr[i]:arr[i]+"~`";
		}
	}*/
	
	if(identity == 0){
		noteajaxcall("POST", "/note", "application/json", "{noteName:"+ noteName + ",noteDescription:"+notesContent+",categoryId:"+categoryId+"}");
		setTimeout(function(){ alert("Note was created successfully !"); }, 1000);
	}else{
		noteajaxcall("PUT", "/note/"+identity, "application/json", "{noteName:"+ noteName + ",noteDescription:"+notesContent+",categoryId:"+categoryId+"}");
		setTimeout(function(){ alert("Note was updated successfully !"); }, 1000);
	}
	
}

function deletenote(id){
	noteajaxcall("DELETE", "/note/"+id, "application/json", "");
	setTimeout(function(){ alert("Note was deleted successfully !"); }, 1000);
}

function noteajaxcall(method, url, content_type, input_data) {

	var xhr = new XMLHttpRequest();
	xhr.open(method, url, true);
	xhr.setRequestHeader('Content-Type', content_type);
	xhr.send(input_data);
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			notesList(this.responseText);
		}
	};
}