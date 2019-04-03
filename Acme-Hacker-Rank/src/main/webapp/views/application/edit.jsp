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

<form:form action="${requestURI}" modelAttribute="applicationForm">
	<form:hidden path="id" />
	<form:hidden path="publicationMoment" />
	<form:hidden path="submissionMoment" />
	<form:hidden path="status" />
	<form:hidden path="problem" />
	<form:hidden path="hacker" />
	<form:hidden path="position" />


	<acme:textbox code="application.textAnswer" path="textAnswer" />
	<acme:textbox code="application.linkAnswer" path="linkAnswer" />

	<jstl:if test="${isRead == false}">
		<acme:submit name="save" code="application.save" />
		<jstl:if test="${id != 0}">
			<acme:delete confirmDelete="application.confirmDelete" name="delete"
				code="application.delete" />
		</jstl:if>
	</jstl:if>

	<security:authorize access="hasRole('COMPANY')">
		<acme:cancel url="application/company/list.do"
			code="application.cancel" />
	</security:authorize>

	<security:authorize access="hasRole('HACKER')">
		<acme:cancel url="application/hacker/list.do"
			code="application.cancel" />
	</security:authorize>
</form:form>