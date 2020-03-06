<%@ include file="partial/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ATM Simulation</title>
    <%@ include file="partial/header.jsp" %>
</head>
<body>
    <div class="container">
        <h1>Welcome</h1>
        <form:form action="/login" method="POST" modelAttribute="account">
          <div class="form-group">
            <label for="accountNumber">Account Number</label>
            <form:input path="accountNumber" class="form-control" id="accountNumber" placeholder="Enter account number" />
            <small><form:errors path="accountNumber" cssClass="errormsg" /></small>
          </div>
          <div class="form-group">
            <label for="pin">PIN</label>
            <form:password path="pin" class="form-control" id="pin" placeholder="PIN" />
            <small><form:errors path="pin" cssClass="errormsg" /></small>
          </div>
          <button type="submit" class="btn btn-primary">Sign In</button>
        </form:form>
    </div>

    <%@ include file="partial/footer.jsp" %>
</body>
</html>