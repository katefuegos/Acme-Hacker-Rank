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


<%-- <h3>
	<spring:message code="position.final" />
</h3> --%>
<display:table name="positionsFinal" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="ticker" titleKey="position.ticker" />
	<display:column property="title" titleKey="position.title" />
	<display:column property="skills" titleKey="position.skills" />
	<display:column property="profile" titleKey="position.profile" />
	<display:column property="tecnologies" titleKey="position.tecnologies" />
	<display:column property="salary" titleKey="position.salary" />

	<display:column>
		<a href="position/display.do?positionId=${row.id}"><spring:message
				code="position.display" /> </a>
	</display:column>
	<display:column>
		<a href="position/company/cancel.do?positionId=${row.id}"><spring:message
				code="position.cancel" /> </a>
	</display:column>


</display:table>

<%-- <h3>
	<spring:message code="position.draft" />
</h3> --%>
<display:table name="positionsDraft" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="ticker" titleKey="position.ticker" />
	<display:column property="title" titleKey="position.title" />
	<display:column property="skills" titleKey="position.skills" />
	<display:column property="profile" titleKey="position.profile" />
	<display:column property="tecnologies" titleKey="position.tecnologies" />
	<display:column property="salary" titleKey="position.salary" />

	<display:column>
		<a href="position/display.do?positionId=${row.id}"><spring:message
				code="position.display" /> </a>
	</display:column>
	<display:column>
		<a href="position/company/edit.do?positionId=${row.id}"><spring:message
				code="position.edit" /> </a>
	</display:column>

</display:table>

<a href="position/company/create.do"> <spring:message
		code="master.page.position.create" />
</a>
