<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="resources/css/conversations.css">
<title>Conversations</title>
</head>
<body>

<section class="content-header">
	<h1>
		Conversations
	</h1>
</section>

<section class="content">
	<div class="box box-primary">
		<c:url var="conversationsUrl" value="" />

		<%@ include file="conversations/latest.jsp"%>
	</div>
</section>

</body>
</html>