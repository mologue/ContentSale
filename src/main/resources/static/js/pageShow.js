
	var $ = function(id){
		return document.getElementById(id);
	}
	
	$('plusNum').onclick = function(e){
		e = window.event || e;
		o = e.srcElement || e.target;
		var num = $('allNum').textContent;
		if(num > 0){
			num --;
			$('allNum').innerHTML = num;
		}else{
			alert("您没有购买任何商品");
		}
	};

	$('addNum').onclick = function(e){
		e = window.event || e;
		o = e.srcElement || e.target;
		var num = $('allNum').textContent;
		num ++;
		$('allNum').innerHTML = num;
	};
	
	var loading = new Loading();
	var layer = new Layer();

	
	$('add').onclick = function(e){
		var ele = e.target;
		var id = ele && ele.dataset.id;
		// var title = ele && ele.dataset.title;
		// var price = ele && ele.dataset.price;
		var num = $('allNum').innerHTML;
		// var contentDetail = {'id':id,'price':price,'title':title,'num':num};
		// var name = 'contents';
		// var contentList1 = new Array;
		// var contentList = util.getCookie(name);

		// ajaxAdd(id,num)
		// if(contentList == "" || contentList == null){
		// 	contentList1.push(contentDetail);
		// 	util.setCookie(name,contentList1);
		// }else if(util.findOne(contentList,id)){
		// 	util.modifyTwo(contentList,id,num);
		// 	util.setCookie(name,contentList);
		// }else{
		// 	contentList.push(contentDetail);
		// 	util.setCookie(name,contentList);
		// }
		console.log(document.cookie);
//		util.deleteCookie(name);
		e == window.event || e;
		layer.reset({
			content:'确认加入购物车吗？',
			onconfirm:function(){
				layer.hide();
				loading.show();
				loading.result('添加购物车成功');
                ajaxAdd(id,num);
			}.bind(this)
		}).show();
		return;
	};

    function ajaxAdd(id,num) {

        ajax({
            data:{"id":id,"num":num},
            url:'/addShopping',
        });
    }





