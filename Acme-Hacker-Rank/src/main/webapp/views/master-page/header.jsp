<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div align="left">
	<a href="#"><img src="${banner}" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message
						code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/action-1.do"><spring:message
								code="master.page.administrator.action.1" /></a></li>
					<li><a href="administrator/action-2.do"><spring:message
								code="master.page.administrator.action.2" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message
						code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/action-1.do"><spring:message
								code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message
								code="master.page.customer.action.2" /></a></li>
				</ul></li>
		</security:authorize>



		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message
						code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="register/actor.do?authority=HACKER"><spring:message
								code="master.page.register.hacker" /></a></li>

					<li><a href="register/actor.do?authority=COMPANY"><spring:message
								code="master.page.register.company" /></a></li>

					<security:authorize access="hasRole('ADMIN')">
						<li><a
							href="register/administrator/newActor.do?authority=ADMIN"><spring:message
									code="master.page.register.admin" /></a></li>
					</security:authorize>
				</ul></li>



		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/action-1.do"><spring:message
								code="master.page.profile.action.1" /></a></li>
					<li><a href="profile/action-2.do"><spring:message
								code="master.page.profile.action.2" /></a></li>
					<li><a href="profile/action-3.do"><spring:message
								code="master.page.profile.action.3" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv" href="welcome/terms.do"><spring:message
						code="master.page.privacyPolicy" /></a></li>
		</security:authorize>
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="welcome/terms.do"><spring:message
						code="master.page.privacyPolicy" /></a></li>
		</security:authorize>



		<security:authorize access="hasRole('COMPANY')">
			<li><a class="fNiv"><spring:message
						code="master.page.position.manage" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="position/company/list.do"><spring:message
								code="master.page.position.list" /></a></li>
					<li><a href="position/company/create.do"><spring:message
								code="master.page.position.create" /></a></li>
				</ul></li>


		</security:authorize>

		
				<li class="arrow"></li>
				<li><a href="position/list.do"><spring:message
							code="master.page.position.list" /></a></li>





	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

