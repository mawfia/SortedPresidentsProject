<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" type="text/css" href="ovalOfficeInside.css"
	media="screen" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Presidents' Bio Page</title>
<link rel="stylesheet" type="text/css" href="ovalOfficeInside.css" />
</head>
<body>
	<fieldset>
		<h2>President
			${applicationScope.presidents[currentTerm].firstName}
			${applicationScope.presidents[currentTerm].lastName}</h2>
		<c:choose>
			<c:when test="${applicationScope.presidents == null}">
				<img
					src="https://www.whitehouse.gov/sites/default/files/imagecache/gallery_img_full/image/image_file/washington.jpg" />
			</c:when>
			<c:otherwise>
				<img src="${applicationScope.presidents[currentTerm].photo}" />
			</c:otherwise>
		</c:choose>

		<table>
			<thead>
				<tr>
					<th colspan="2">Biography</th>
				<tr>
			</thead>
			<tr>
				<td>Term Number:</td>
				<td>${applicationScope.presidents[currentTerm].termNumber}</td>
			</tr>
			<tr>
				<td>Start Year:</td>
				<td>${applicationScope.presidents[currentTerm].startDate}</td>
			</tr>
			<tr>
				<td>End Year:</td>
				<td>${applicationScope.presidents[currentTerm].endDate}</td>
			</tr>
			<tfoot>
				<tr>
					<th>Fun Fact:</th>
					<th>${applicationScope.presidents[currentTerm].funFact}</th>
				</tr>
			</tfoot>
		</table>

		<br />
		<form action="presidents.do" method="POST">
			<input list="browsers" name="browsers" placeholder="Term Number">
			<datalist id="browsers" >
				<c:forEach var="president" items="${applicationScope.presidents}">
					<option value="${president.termNumber}">${president.lastName}, ${president.firstName}</option>
				</c:forEach>
			</datalist>
			<button name="operation" value="Previous">Previous</button>
			<button name="operation" value="Home">Home</button>
			<button name="operation" value="Next">Next</button>
			<input type="submit" value="Submit" name="browsers" autofocus>
		</form>
	</fieldset>
	<br />
	<fieldset>
		<legend>Sort/Search Options</legend>
		<form action="presidents.do" method="POST">
			<select name="sort">
				<option value="0">Alphabetical (Ascending Order)</option>
				<option value="1">Alphabetical (Descending Order)</option>
				<option value="2">Short Term (4 Years or less)</option>
				<option value="3">Long Term (More than 4 years)</option>
			</select><br /> <input type="submit" value="Sort">
		</form>

		<form action="presidents.do" method="POST">
			Search:<input type="text" name="search" placeholder="Input Text"> 
			<input type="submit" value="Search"> <input type="reset"><br />
			<input type="radio" name="input" value="4" checked> First Name 
			<input type="radio" name="input" value="5"> Last Name 
			<input type="radio" name="input" value="6"> Party 
			<input type="radio" name="input" value="7"> Fun Fact
		</form>
	</fieldset>
</body>
</html>