(function() {
	window.CookieUtil = {
		/**
		 * 添加cookie
		 * 
		 * @param name
		 *            -cookie名
		 * @param value
		 *            -cookie值
		 * @param expiresDay
		 *            -cookie过期时间
		 */
		setCookie : function(name, value, expiresDay) {
			var date = new Date();
			date.setTime(date.getTime() + expiresDay * 24 * 60 * 60 * 1000);
			document.cookie = name + "=" + value + "; expires="
					+ date.toUTCString();
		},

		/**
		 * 通过设置cookie过期时间删除cookie
		 * 
		 * @param name
		 *            -待删除的cookie名
		 * @param value
		 *            -待删除的cookie值
		 *            过期时间小于当前系统时间，就会被删除
		 */
		removeCookie : function(name, value) {
			var date = new Date();
			date.setTime(date.getTime() -3600);
			document.cookie = name + "=" + value + "; expires="
					+ date.toUTCString();
		},

		/**
		 * 根据cookie名获取cookie值
		 */
		getCookie : function(cname) {
			var name = cname + "=";
			var ca = document.cookie.split(';');
			for (var i = 0; i < ca.length; i++) {
				var c = ca[i].trim();
				if (c.indexOf(name) == 0)
					return c.substring(name.length, c.length);
			}
			return "";
		}
	}

})();