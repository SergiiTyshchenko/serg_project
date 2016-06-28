<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
    <title>Details</title>
    <body>
        <h1>Details</h1>
        Details for ${organization.name}<br><br>
            <img src="${organization.imageUrl}"/> <br/>
        <a href="../organizations">Back to Listing</a>
    </body>
</html>