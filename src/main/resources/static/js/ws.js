
var websocket = null;
function openConnect(){
	if(websocket != null){
		alert("你已经在房间里了，请不要重复登录");
		return;
	}
	var message = document.getElementById('nickName').value;
	if(message==null||message=='') {
		alert("必须输入昵称");
		return;
	}
	if('WebSocket' in window){
		websocket = new WebSocket("ws://127.0.0.1:8080/websocket/" + message);
		//连接发生错误的回调方法
		websocket.onerror = function(){
			setMessageInnerHTML("error");
		};

		//连接成功建立的回调方法
		websocket.onopen = function(event){
			setMessageInnerHTML("open");
		}

		//接收到消息的回调方法
		websocket.onmessage = function(event){
			if(event.data.substr(0,6)=="当前房间有："){
				setMessageInnerHTML(event.data);
			}else{
                rtotal = document.form1.runningtotal.value;
				document.form1.runningtotal.value = event.data + "\n\n" + rtotal;
			}
		}

		//连接关闭的回调方法
		websocket.onclose = function(){
			setMessageInnerHTML("close");
		}

		//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
		window.onbeforeunload = function(){
			websocket.close();
		}

		//将消息显示在网页上
		function setMessageInnerHTML(innerHTML){
			document.getElementById('message').innerHTML = innerHTML + '<br/>';
		}
	}else{
		alert('Not support websocket')
	}
}

//关闭连接
function closeWebSocket(){
	websocket.close();
    websocket=null;
}

//发送消息
function send(){
	if(websocket == null){
		alert("请先输入昵称并连接");
		return;
	}
	var message = document.getElementById('text').value;
	websocket.send(message);
}

//判断当前浏览器是否支持WebSocket

