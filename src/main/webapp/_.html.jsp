<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:url value="/req/admin/login.html" var="reqLoginView" />
<html>
<head>
<script type="text/javascript">
	window.location.href = "${reqLoginView}";
</script>
</head>
</html>
