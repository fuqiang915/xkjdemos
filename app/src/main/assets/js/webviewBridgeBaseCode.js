// 注意这里的代码必须使用(function(){})()包裹
 (function(){
    window._callbacks = {};
    window._callbacks_id = 0;
    window.UAPPJSBridge ={
        weixinshare:function(message,success,error){
            var id = _callbacks_id++;
            _callbacks[id]={
                success:success,
                error:error
            };
            window.location.href= "uapp://weixinshare?message='i am message '&callback_id="+id;
            return id;
        }
    };
    /*  这个注释应该没有什么问题*/
})();