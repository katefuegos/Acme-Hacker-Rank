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


<!-- POSITION DATA -->

<p>Position datas</p>



<display:table name="positiondatas" id="row" requestURI="${requestURI}"
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
	
	<display:column property="curricula.fullName" titleKey="positionData.curricula" />
	
	<display:column property="startDate" titleKey="positionData.startDate" />
	
	<display:column property="endDate" titleKey="positionData.endDate" />
	
</display:table>

<security:authorize access="hasRole('HACKER')">
	<a href="positionData/hacker/create.do"> <spring:message
					code="positionData.create" />
			</a>
</security:authorize>

<!-- MISCELANEOUS DATA -->
<p>Miscellaneous datas</p>

<display:table name="miscellaneousdatas" id="row" requestURI="${requestURI}"
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
	
	<display:column property="curricula.fullName" titleKey="miscellaneousData.curricula" />
	
	
</display:table>

<security:authorize access="hasRole('HACKER')">
	<a href="miscellaneousData/hacker/create.do"> <spring:message
					code="miscellaneousData.create" />
			</a>
</security:authorize>

<p>Education datas</p>

<display:table name="educationdatas" id="row" requestURI="${requestURI}"
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
	
	<display:column property="curricula.fullName" titleKey="educationData.curricula" />
	
	<display:column property="mark" titleKey="educationData.mark" />
	
	<display:column property="startDate" titleKey="educationData.startDate" />
	
	<display:column property="endDate" titleKey="educationData.endDate" />
	
</display:table>

<security:authorize access="hasRole('HACKER')">
	<a href="educationData/hacker/create.do"> <spring:message
					code="educationData.create" />
			</a>
</security:authorize>
