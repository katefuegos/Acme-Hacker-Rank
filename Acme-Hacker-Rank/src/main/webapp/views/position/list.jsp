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
	
	<acme:textbox code="position.search" path="keyword"/>
<acme:submit name="save" code="position.search"/>
	
</form:form>

<display:table name="positions" id="row" requestURI="${requestURI}"
	pagesize="15" class="displaytag">
	
		<display:column property="ticker" titleKey="position.ticker" />
		<display:column property="title" titleKey="position.title" />
		<display:column property="skills" titleKey="position.skills" />
		<display:column property="profile" titleKey="position.profile" />
		<display:column property="tecnologies" titleKey="position.tecnologies" />
		<display:column property="salary" titleKey="position.salary" />
		
		<display:column >
			<a href="position/display.do?positionId=${row.id}"><spring:message code="position.display" /> </a>
		</display:column>
		<display:column >
			<a href="position/displayCompany.do?positionId=${row.id}"><spring:message code="position.display.company" /> </a>
		</display:column>

</display:table>

