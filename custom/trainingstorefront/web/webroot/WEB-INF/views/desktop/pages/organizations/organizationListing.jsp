<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
    <title>Listing</title>
    <body>
        <h1>Listing</h1>
     <ul>
     <c:forEach var="organization" items="${organizations}">
        <li >
            <a href="./organizations/${organization.name}">${organization.name}</a>
            <img style="margin-left:20px;vertical-align:top;margin-bottom:20px" src="${organization.imageUrl}"/>
        </li>
      </c:forEach>
      </ul>
    </body>
</html>