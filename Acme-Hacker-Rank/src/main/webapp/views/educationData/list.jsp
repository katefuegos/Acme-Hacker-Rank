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

<display:table name="educationDatas" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">


<security:authorize access="hasRole('HACKER')">

	<display:column>
	<a href="educationData/hacker/show.do?educationDataId=${row.id}"> <spring:message
					code="educationData.show" />
			</a>
	</display:column>
	
	<display:column>
	<a href="educationData/hacker/edit.do?educationDataId=${row.id}"> <spring:message
					code="educationData.edit" />
			</a>
	</display:column>
	
</security:authorize>
	<display:column property="degree" titleKey="educationData.degree" />
	
	<display:column property="institution" titleKey="educationData.institution" />
	
	<display:column property="curricula.title" titleKey="educationData.curricula" />
	
	<display:column property="mark" titleKey="educationData.mark" />
	
	<display:column property="startDate" titleKey="educationData.startDate" />
	
	<display:column property="endDate" titleKey="educationData.endDate" />
	
</display:table>

<security:authorize access="hasRole('HACKER')">
	<a href="educationData/hacker/create.do"> <spring:message
					code="educationData.create" />
			</a>
</security:authorize>