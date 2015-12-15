<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="posts" type="java.util.List<bodiukh.blog.domain.Post>"--%>

<html>
<body>
<c:forEach items="${posts}" var="post">
  <tr>
    <td>${post.author}</td>
    <td>${post.text}</td>
  </tr>
</c:forEach>
</body>
</html>