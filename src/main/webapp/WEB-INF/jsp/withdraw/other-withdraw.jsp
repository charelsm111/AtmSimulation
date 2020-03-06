<%@ include file="../partial/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Other Withdraw</title>
    <%@ include file="../partial/header.jsp" %>
</head>

<body>
    <div class="container">
        <div class="row top5">
            <div class="col-sm text-center">
                <h1>Ohter Withdraw</h1>
            </div>
        </div>
        <br />
        <p><a href="/account" class="btn btn-warning">Back</a></p>
        <form:form id="form-other-withdraw" action="/other-withdraw" method="post" modelAttribute="withdraw">
            <div class="row justify-content-sm-center">
                <div class="col-sm-6 text-center">
                    <div class="form-group">
                        <label for="amount">Amount</label>
                        <form:input path="amount" class="form-control" id="amount" placeholder="Enter amount" />
                        <small><form:errors path="amount" cssClass="errormsg" /></small>
                    </div>
                </div>
            </div>
            <div class="row justify-content-sm-center top5">
                <div class="col-sm-6 text-center">
                    <input type="submit" value="Submit" class="btn btn-primary btn-lg btn-block withdraw">
                </div>
            </div>
        </form:form>
    </div>

    <%@ include file="../partial/footer.jsp" %>
</body>
</html>