<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title></title>

<link rel="stylesheet" type="text/css" href="styles/newsStyle.css">
</head>
<body>

<h1>
Error
</h1>

<c:if test="${not (param.error eq null)}">
		<font color="red"> <c:out value="${param.error}" />
		</font>
	</c:if>
	<br><br>

	back to the
	<a href="index.jsp">main page</a>
	
	<br><br>

</body>
</html>