<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<center>
	<h4>类别</h4>
		<table style="width: 600px" border="1">
			<tr>
				<c:forEach items="${requestScope.key }" var="k">
					<th>"${k}"</th>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach items="${requestScope.value }" var="v">
					<td>"${v}"</td>
				</c:forEach>
			</tr>
		</table>
	</center>


</body>
</html>