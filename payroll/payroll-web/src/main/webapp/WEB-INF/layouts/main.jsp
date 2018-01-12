<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../partials/head.jsp"%>
</head>
<body>
	<%@include file="../partials/header.jsp"%>
	${content}
	<%@include file="../partials/footer.jsp"%>
</body>
</html>