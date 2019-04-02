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

<form:form action="position/company/edit.do"
	modelAttribute="position">

	<acme:textbox code="ticker" path="ticker" readonly="${readonly}"/>
	<acme:textbox code="title" path="title" readonly="${readonly}"/>
	<acme:textbox code="description" path="description" readonly="${readonly}"/>
	<acme:textbox code="deadline" path="deadline" readonly="${readonly}"/>
	<acme:textbox code="skills" path="skills" readonly="${readonly}"/>
	<acme:textbox code="profile" path="profile" readonly="${readonly}"/>
	<acme:textbox code="tecnologies" path="tecnologies" readonly="${readonly}"/>
	<acme:textbox code="salary" path="salary" readonly="${readonly}"/>
	
	<form:label path="draftMode">
		<spring:message code="position.draftMode" />
	</form:label>
	<form:checkbox path="draftMode" readonly="true"/>
	<form:errors path="draftMode" cssClass="error" />
	<br />
	<br />

	
	<acme:submit name="save" code="position.save"/>
	<acme:cancel url="position/company/list.do" code="position.cancel"/>
	<acme:delete confirmDelete="position.confirmDelete" name="delete" code="position.delete"/>
		

</form:form>