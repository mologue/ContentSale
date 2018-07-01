<!DOCTYPE html>
<html>
<#include "/include/head.ftl">

<style type="text/css">
    .m-form-ht .fmitem-nolab:before{display:table-cell;content:" ";}
</style>
<body>
<#include "/include/support.ftl">
<#include "/include/header.ftl">
<form class="m-form m-form-ht n-login" id="loginForm" onsubmit="return false;" autocomplete="off">
    <div class="fmitem">
        <label class="fmlab">用户名：</label>
        <div class="fmipt">
            <input class="u-ipt" name="userName" autofocus/>
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt">
            <input class="u-ipt" type="password" name="password"/>
        </div>
    </div>

    <div class="fmitem">
        <label class="fmlab">类型：</label>
        <div class="fmipt">
            <select class="u-ipt" name="register" autofocus style="width:  100%;background: #FAFFBD;">
                <option value ="0">buyer</option>
                <option value ="1">seller</option>
            </select>
        </div>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block">注册</button>
        </div>
    </div>

    </div>
</form>

<#include "/include/footer.ftl">
<#--<script type="text/javascript" src="/js/md5.js"></script>-->
<script type="text/javascript" src="/js/sha.js"></script>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/register.js"></script>
</body>
</html>