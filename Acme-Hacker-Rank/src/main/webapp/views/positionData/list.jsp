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

<display:table name="positionDatas" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">


<security:authorize access="hasRole('HACKER')">

	<display:column>
	<a href="positionData/hacker/show.do?positionDataId=${row.id}"> <spring:message
					code="positionData.show" />
			</a>
	</display:column>
	
	<display:column>
	<a href="positionData/hacker/edit.do?positionDataId=${row.id}"> <spring:message
					code="positionData.edit" />
			</a>
	</display:column>
	
</security:authorize>
	<display:column property="title" titleKey="positionData.title" />
	
	<display:column property="description" titleKey="positionData.description" />
	
	<display:column property="curricula.title" titleKey="positionData.curricula" />
	
	<display:column property="startDate" titleKey="positionData.startDate" />
	
	<display:column property="endDate" titleKey="positionData.endDate" />
	
</display:table>

<security:authorize access="hasRole('HACKER')">
	<a href="positionData/hacker/create.do"> <spring:message
					code="positionData.create" />
			</a>
</security:authorize>