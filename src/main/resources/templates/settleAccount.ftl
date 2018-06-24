<!DOCTYPE html>
<html>
<#include "/include/head.ftl">
<body>
<#include "/include/support.ftl">
<#include "/include/header.ftl">
<div class="g-doc" id="settleAccount">
    <div class="m-tab m-tab-fw m-tab-simple f-cb" >
        <h2>已添加到购物车的内容</h2>
    </div>
 	<table id="newTable" class="m-table m-table-row n-table g-b3">
<#if shoppingItemList?? && (shoppingItemList?size > 0) >
		<tr>
        <th>内容名称</th>
        <th> 数量</th>
        <th> 价格</th>
        </tr>
	<#list shoppingItemList as x>
		<tr id="${x.contentId}">
			<td>${x.title}</td>
			<td>${x.amount}</td>
			<td>${x.price}</td>
		</tr>
		</#list>
</#if>
 	</table>
 	<div id="act-btn"><button class="u-btn u-btn-primary" id="back">退出</button>
 	<button class="u-btn u-btn-primary" id="Account">购买</button></div>
</div>
<#include "/include/footer.ftl">
<script type="text/javascript" src="../js/global.js"></script>
<script type="text/javascript" src="../js/settleAccount.js"></script>
</body>
</html>