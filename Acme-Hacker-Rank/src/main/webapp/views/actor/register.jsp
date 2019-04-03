<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" modelAttribute="actorForm">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="userAccount.enabled" />
	<form:hidden path="auth" />


	<acme:textbox code="actor.userAccount.username"
		path="userAccount.username" />
	<acme:password code="actor.userAccount.password"
		path="userAccount.password" />
	<acme:textbox code="actor.name" path="name" />
	<acme:textbox code="actor.surnames" path="surname" />
	<acme:textbox code="actor.VATnumber" path="VATNumber" />
	<acme:textbox code="actor.photo" path="photo" />
	<acme:textbox code="actor.email" path="email" />
	<acme:textbox code="actor.phone" path="phone" />
	<acme:textbox code="actor.address" path="address" />

	<jstl:if test="${actorForm.auth != 'COMPANY'}">
		<form:hidden path="comercialName" />

	</jstl:if>

	<jstl:if test="${actorForm.auth == 'COMPANY' }">
		<acme:textbox code="actor.comercialName" path="comercialName" />

	</jstl:if>
	<br>
	<h5>
	<spring:message code="actor.add.creditcard" />
	</h5>
	<acme:textbox code="actor.holderName" path="holderName" />
	<acme:textbox code="actor.brandName" path="brandName" />
	<acme:textbox code="actor.number" path="number" />
	<acme:textbox code="actor.expirationMonth" path="expirationMonth" />
	<acme:textbox code="actor.expirationYear" path="expirationYear" />
	<acme:textbox code="actor.CVVCode" path="CVVCode" />



	<form:label path="checkTerms">
		<spring:message code="actor.check" />
	</form:label>
	<form:checkbox path="checkTerms" readonly="${isRead}" />
	<form:errors cssClass="error" path="checkTerms" />
	<br />
	<input type="button" name="viewPolicy"
		value="<spring:message code="actor.viewPolicy" />"
		onclick="javascript: relativeRedir('welcome/terms.do');" />
	<br />
	<br />
	<script type="text/javascript">
		function isValid() {
			var phoneRe = /^(((\+[1-9][0-9]{0,2}) \(([1-9][0-9]{0,2})\) (\d\d\d\d+))|((\+[1-9][0-9]{0,2}) (\d\d\d\d+))|((\d\d\d\d+)))$/;
			var digits = document.getElementById('tlf').value;
			var res = phoneRe.test(digits);
			if (res) {
				return true;
			} else {
				return confirm('<spring:message code="phone.confirm" />');
			}
		}
	</script>

		<acme:submit name="save" code="actor.save" />


	<acme:cancel url="welcome/index.do" code="message.cancel" />

</form:form>
