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

<display:table name="miscellaneousDatas" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">


<security:authorize access="hasRole('HACKER')">

	<display:column>
	<a href="miscellaneousData/hacker/show.do?miscellaneousDataId=${row.id}"> <spring:message
					code="miscellaneousData.show" />
			</a>
	</display:column>
	
	<display:column>
	<a href="miscellaneousData/hacker/edit.do?miscellaneousDataId=${row.id}"> <spring:message
					code="miscellaneousData.edit" />
			</a>
	</display:column>
	
</security:authorize>
	<display:column property="text" titleKey="miscellaneousData.text" />
	
	<display:column property="attachments" titleKey="miscellaneousData.attachments" />
	
	<display:column property="curricula.title" titleKey="miscellaneousData.curricula" />
	
	
</display:table>

<security:authorize access="hasRole('HACKER')">
	<a href="miscellaneousData/hacker/create.do?curriculaId=${row.id}"> <spring:message
					code="miscellaneousData.create" />
			</a>
</security:authorize>