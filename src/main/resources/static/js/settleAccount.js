(function(w,d,u){
	var settleAccount = util.get('settleAccount');
	if(!settleAccount){
		return;
	}
	var name = 'contents';
	var trds = document.getElementsByTagName("tr");
	var contents=[];
	if(trds.length > 1){
		var i
		for(i=1;i<trds.length;i++){
			var tds = trds[i];
			contents.push({"id":tds.id,
							"num":tds.children[1].innerText.replace(",","")})
		}
	}
	var $ = function(id){
		return document.getElementById(id);
	}




	window.onload = function(){
		$('newTable').onclick = function(e){
			var e = arguments[0] || window.event;
			target = e.srcElement ? e.srcElement : e.target;
			if(target.nodeName == "SPAN" && target.className == "moreNum"){
				var num = target.parentElement.children[1].textContent;
				var id = target.parentElement.children[2].textContent;
				num ++;
				target.parentElement.children[1].textContent = num;
				util.modifyOne(contents,id,num);
			}else if(target.nodeName == "SPAN" && target.className == "lessNum"){
				var num = target.parentElement.children[1].textContent;
				var id = target.parentElement.children[2].textContent;
				num --;
				if(num < 0){
					alert("该商品数量为0");
				}else{
					target.parentElement.children[1].textContent = num;
					util.modifyOne(contents,id,num);
				}
			}
			return false;
		};
	};

	var loading = new Loading();
	var layer = new Layer();
	$('Account').onclick = function(e){
            var newcontents = contents.map(function (arr) {
                return {'contentId': arr.id, 'number': arr.num};
            });
		console.log(newcontents);
		var ele = e.target;
			layer.reset({
				content:'确认购买吗？',
				onconfirm:function(){
					layer.hide();
					loading.show();
					
					var xhr = new XMLHttpRequest();
					var data = JSON.stringify(newcontents);
					xhr.onreadystatechange = function(){
						 if(xhr.readyState == 4){
				                var status = xhr.status;
				                if(status >= 200 && status < 300 || status == 304){
				                	var json = JSON.parse(xhr.responseText);
				                	if(json && json.code == 200){
				                		loading.result('购买成功',function(){location.href = './account';});
				                		util.deleteCookie(name);
				                	}else{
				                		alert(json.message);
				                	}
				                }else{
				                	loading.result('购买失败');
				                }
				            }
					};
					 xhr.open('post','/api/buy');
					 xhr.setRequestHeader('Content-Type','application/json');
					 xhr.send(data);
				}.bind(this)
			}).show();
			return;
	};
	$('back').onclick = function(){
		location.href = window.history.back();
	}
})(window,document);