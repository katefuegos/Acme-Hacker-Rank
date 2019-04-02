<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="position/search.do"
	modelAttribute="searchForm">
	
	<acme:textbox code="position.keyword" path="keyword"/>
<acme:submit name="save" code="position.search"/>
	
</form:form>

<display:table name="positions" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
		<display:column property="ticker" titleKey="ticker" />
		<display:column property="title" titleKey="ticker" />
		<display:column property="skills" titleKey="ticker" />
		<display:column property="profile" titleKey="ticker" />
		<display:column property="tecnologies" titleKey="ticker" />
		<display:column property="salary" titleKey="ticker" />
		
		<display:column >
			<a href="position/display.do?positionId=${row.id}"><spring:message code="position.display" /> </a>
		</display:column>

</display:table>

