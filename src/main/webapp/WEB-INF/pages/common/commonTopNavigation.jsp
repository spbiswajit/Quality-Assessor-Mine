<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div id='cssmenu'>
	<ul>

		<li><a style="padding:6px 2px 7px 1px" href='/qualityassessor/home'><span>Home</span></a></li>
		<li class="has-sub"><a href='/qualityassessor/myTemplates'><span>Template
					Management</span></a>

			<ul>
				<li class="level-2"><a href="/qualityassessor/myTemplates"><span>My
							Templates</span></a></li>
				<li class="level-2"><a href="/qualityassessor/domains?domainType=product"><span>Product
							Templates</span></a></li>
				<li class="level-2"><a href="/qualityassessor/domains?domainType=skill"><span>Skill
							Templates</span></a></li>
				<li class="level-2"><a href="/qualityassessor/domains?domainType=practice"><span>Practice
							Templates</span></a></li>
				<li class="level-2"><a href="/qualityassessor/domains?domainType=environment"><span>Environment
							Templates</span></a></li>		
			</ul></li>

		<%-- 			<c:if test="${sessionScope.group=='AdminGroup'}"> --%>
		<li class="has-sub"><a href='/qualityassessor/myProducts'><span>Product
					Management</span></a>

			<ul>
				<li><a href="/qualityassessor/myProducts"><span>My Products</span></a></li>
				<li><a href="/qualityassessor/domains?domainType=product"><span>Product
							Templates</span></a></li>
				<li><a href="/qualityassessor/domains?domainType=environment"><span>Environment
							Templates</span></a></li>
				<li><a href="/qualityassessor/productReviews"><span>Product Reviews</span></a></li>
				<li><a href="/#"><span>Environment Reviews</span></a></li>
			</ul></li>
		<li class="has-sub"><a href='/qualityassessor/domains?domainType=skill'><span>Colleague/Skill
					Management</span></a>

			<ul>
				<li><a href="/qualityassessor/domains?domainType=skill">Skill Templates</a></li>
				<li><a href="/qualityassessor/domains?domainType=practice">Practice Templates</a></li>
				<li><a href="#">Colleague Reviews</a></li>
				<li><a href="#">My Colleagues</a></li>
			</ul></li>
		<%-- 			</c:if> --%>



		<li class="has-sub"><a href='/qualityassessor/assessmentRequests'><span>Reviews</span></a>

			<ul>
				<li><a href="/qualityassessor/assessmentRequests">Review Requests</a></li>
				<li><a href="/qualityassessor/productReviews">Product Reviews</a></li>
				<li><a href="#">Colleague Reviews</a></li>
				<li><a href="#">Environment Reviews</a></li>
				<li><a href="#">Practice Reviews</a></li>
			</ul></li>


	</ul>
	<div style="padding: 3px 10px; position: absolute; right: 220px;cursor:pointer">
		<a style="text-decoration:none;cursor:pointer" href="#">
		<img style="width: 12px; height: 12px" 
			src="/qualityassessor/images/plus.png"> <label style="color: white;cursor:pointer">Create
			Review</label></a>
	</div>
	<div style="position: absolute; color: white; right: 10px;" id="search">
		<input size="30" type="text">
	</div>
</div>
